package com.qlcx.framework.shiro.web.filter;

import java.io.Serializable;
import java.util.Deque;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlcx.common.constant.Constants;
import com.qlcx.common.constant.ShiroConstants;
import com.qlcx.common.utils.MessageUtils;
import com.qlcx.common.utils.StringUtils;
import com.qlcx.framework.manager.AsyncManager;
import com.qlcx.framework.manager.factory.AsyncFactory;
import com.qlcx.framework.util.ShiroUtils;
import com.qlcx.system.domain.SysUser;

/**
 * Exit filter
*/
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter
{
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * Redirected address after exit
    */
    private String loginUrl;

    private Cache<String, Deque<Serializable>> cache;

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
                SysUser user = ShiroUtils.getSysUser();
                if (StringUtils.isNotNull(user))
                {
                    String loginName = user.getLoginName();
                    // Record user logout
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
                    // clear the cache
                    cache.remove(loginName);
                }
                // sign out
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            log.error("Encountered session exception during logout. This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * Exit Jump URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }

    // Set the prefix of the Cache key
    public void setCacheManager(CacheManager cacheManager)
    {
        // Must be the same as the cache name in the ehcache cache configuration
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USERCACHE);
    }
}
