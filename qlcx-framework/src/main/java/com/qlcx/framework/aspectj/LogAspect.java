package com.qlcx.framework.aspectj;

import java.lang.reflect.Method;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qlcx.common.annotation.Log;
import com.qlcx.common.enums.BusinessStatus;
import com.qlcx.common.json.JSON;
import com.qlcx.common.utils.ServletUtils;
import com.qlcx.common.utils.StringUtils;
import com.qlcx.framework.manager.AsyncManager;
import com.qlcx.framework.manager.factory.AsyncFactory;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysOperLog;
import com.qlcx.system.domain.SysUser;

/**
 * Operation log record processing
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    // Configure weaving point
    @Pointcut("@annotation(com.qlcx.common.annotation.Log)")
    public void logPointCut()
    {
    }

    /**
     * Executed after processing the request
     *
     * @param joinPoint pointcut
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * Intercept abnormal operations
     *
     * @param joinPoint pointcut
     * @param e exception
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // get comment
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }

            // Get the current user
            SysUser currentUser = ShiroUtils.getSysUser();

            // *======== Database log =========*//
            SysOperLog operLog = new SysOperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // The requested address
            String ip = ShiroUtils.getIp();
            operLog.setOperIp(ip);
            // return parameter
            operLog.setJsonResult(JSON.marshal(jsonResult));

            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (currentUser != null)
            {
                operLog.setOperName(currentUser.getLoginName());
                if (StringUtils.isNotNull(currentUser.getDept())
                        && StringUtils.isNotEmpty(currentUser.getDept().getDeptName()))
                {
                    operLog.setDeptName(currentUser.getDept().getDeptName());
                }
            }

            if (e != null)
            {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // Set method name
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // Set the request method
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // Handle the parameter on the setting annotation
            getControllerMethodDescription(controllerLog, operLog);
            // Save the database
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        }
        catch (Exception exp)
        {
            // Record local exception log
            log.error("==Advance notice exception==");
            log.error("Exception information: {}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * Obtain the description information of the method in the annotation for the annotation of the Controller layer
     *
     * @param log log
     * @param operLog operation log
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, SysOperLog operLog) throws Exception
    {
        // Set action
        operLog.setBusinessType(log.businessType().ordinal());
        // Set the title
        operLog.setTitle(log.title());
        // Set the operator category
        operLog.setOperatorType(log.operatorType().ordinal());
        // Do you need to save the request, parameters and values
        if (log.isSaveRequestData())
        {
            // Get the parameter information and transfer it to the database.
            setRequestValue(operLog);
        }
    }

    /**
     * Get the requested parameters and put them in the log
     *
     * @param operLog operation log
     * @throws Exception
     */
    private void setRequestValue(SysOperLog operLog) throws Exception
    {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.marshal(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 2000));
    }

    /**
     * Whether there is an annotation, if it exists, get it
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
