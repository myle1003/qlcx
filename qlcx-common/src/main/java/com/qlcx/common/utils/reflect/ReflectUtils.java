package com.qlcx.common.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlcx.common.core.text.Convert;
import com.qlcx.common.utils.DateUtils;

/**
 * Reflection tool class. Provides tool functions such as calling getter/setter methods, accessing private variables, calling private methods, and obtaining generic types Class, real classes that have been AOP.
 */
@SuppressWarnings("rawtypes")
public class ReflectUtils
{
    private static final String SETTER_PREFIX = "set";

    private static final String GETTER_PREFIX = "get";

    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    private static Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     *Gọi phương thức Getter.
     * Hỗ trợ nhiều cấp độ, chẳng hạn như: tên đối tượng. tên của môn học. phương pháp
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeGetter(Object obj, String propertyName)
    {
        Object object = obj;
        for (String name: StringUtils.split(propertyName, "."))
        {
            String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(name);
            object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
        }
        return (E) object;
    }

    /**
     * Gọi phương thức Setter để chỉ khớp với tên phương thức.
     * Hỗ trợ nhiều cấp độ, chẳng hạn như: tên đối tượng. tên của môn học. phương pháp
     */
    public static <E> void invokeSetter(Object obj, String propertyName, E value)
    {
        Object object = obj;
        String[] names = StringUtils.split(propertyName, ".");
        for (int i = 0; i < names.length; i++)
        {
            if (i < names.length - 1)
            {
                String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(names[i]);
                object = invokeMethod(object, getterMethodName, new Class[] {}, new Object[] {});
            }
            else
            {
                String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(names[i]);
                invokeMethodByName(object, setterMethodName, new Object[] { value });
            }
        }
    }

    /**
     * Đọc trực tiếp các giá trị thuộc tính của đối tượng, bỏ qua các phần bổ trợ riêng tư / được bảo vệ mà không cần thông qua các hàm getter.
     */
    @SuppressWarnings("unchecked")
    public static <E> E getFieldValue(final Object obj, final String fieldName)
    {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null)
        {
            logger.debug("In [" + obj.getClass() + "], [" + fieldName + "] field was not found");
            return null;
        }
        E result = null;
        try
        {
            result = (E) field.get(obj);
        }
        catch (IllegalAccessException e)
        {
            logger.error("Exception impossible to throw{}", e.getMessage());
        }
        return result;
    }

    /**
     * Đặt trực tiếp giá trị thuộc tính đối tượng, bỏ qua công cụ sửa đổi private / protected mà không cần thông qua hàm setter.
     */
    public static <E> void setFieldValue(final Object obj, final String fieldName, final E value)
    {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null)
        {
            // throw new IllegalArgumentException("In [" + obj.getClass() + "], [" + fieldName + "] field "was not found");
            logger.debug("In [" + obj.getClass() + "], [" + fieldName + "] field was not found");
            return;
        }
        try
        {
            field.set(obj, value);
        }
        catch (IllegalAccessException e)
        {
            logger.error("Exception that cannot be thrown: {}", e.getMessage());
        }
    }

    /**
     * Trực tiếp gọi các phương thức đối tượng, bỏ qua các bổ ngữ riêng tư / được bảo vệ.
     * Được sử dụng để gọi một lần, nếu không bạn nên sử dụng hàm getAccessibleMethod () để gọi Phương thức nhiều lần.
     * Khớp đồng thời tên phương thức + kiểu tham số,
     */
    @SuppressWarnings("unchecked")
    public static <E> E invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
            final Object[] args)
    {
        if (obj == null || methodName == null)
        {
            return null;
        }
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null)
        {
            logger.debug("In [" + obj.getClass() + "], [" + methodName + "] method was not found");
            return null;
        }
        try
        {
            return (E) method.invoke(obj, args);
        }
        catch (Exception e)
        {
            String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
            throw convertReflectionExceptionToUnchecked(msg, e);
        }
    }

    /**
      * Trực tiếp gọi phương thức đối tượng, bỏ qua phần bổ trợ riêng tư / được bảo vệ,
      * Được sử dụng cho cuộc gọi một lần, nếu không bạn nên sử dụng hàm getAccessibleMethodByName () để nhận Phương thức liên tục sau khi gọi.
      * Chỉ trùng tên hàm, nếu có nhiều hàm trùng tên thì gọi hàm đầu tiên.
      */
      @SuppressWarnings("unchecked")
      public static <E> E invokeMethodByName(final Object obj, final String methodName, final Object[] args)
      {
        Method method = getAccessibleMethodByName(obj, methodName, args.length);
        if (method == null)
        {
            // Nếu nó trống, không có lỗi nào được báo cáo và nó trả về trống trực tiếp.
            logger.debug("In [" + obj.getClass() + "], [" + methodName + "] method was not found");
            return null;
        }
        try
        {
            // Chuyển đổi kiểu (chuyển đổi kiểu dữ liệu tham số thành kiểu tham số phương pháp đích)
            Class<?>[] cs = method.getParameterTypes();
            for (int i = 0; i < cs.length; i++)
            {
                if (args[i] != null && !args[i].getClass().equals(cs[i]))
                {
                    if (cs[i] == String.class)
                    {
                        args[i] = Convert.toStr(args[i]);
                        if (StringUtils.endsWith((String) args[i], ".0"))
                        {
                            args[i] = StringUtils.substringBefore((String) args[i], ".0");
                        }
                    }
                    else if (cs[i] == Integer.class)
                    {
                        args[i] = Convert.toInt(args[i]);
                    }
                    else if (cs[i] == Long.class)
                    {
                        args[i] = Convert.toLong(args[i]);
                    }
                    else if (cs[i] == Double.class)
                    {
                        args[i] = Convert.toDouble(args[i]);
                    }
                    else if (cs[i] == Float.class)
                    {
                        args[i] = Convert.toFloat(args[i]);
                    }
                    else if (cs[i] == Date.class)
                    {
                        if (args[i] instanceof String)
                        {
                            args[i] = DateUtils.parseDate(args[i]);
                        }
                        else
                        {
                            args[i] = DateUtil.getJavaDate((Double) args[i]);
                        }
                    }
                }
            }
            return (E) method.invoke(obj, args);
        }
        catch (Exception e)
        {
            String msg = "method: " + method + ", obj: " + obj + ", args: " + args + "";
            throw convertReflectionExceptionToUnchecked(msg, e);
        }
    }

    /**
     * Chuyển đổi vòng lặp lên trên, lấy DeclaredField của đối tượng và buộc nó có thể truy cập được.
    * Nếu bạn vẫn không thể tìm thấy đối tượng sau khi chuyển đổi hướng lên, hãy trả về null.
    */
    public static Field getAccessibleField(final Object obj, final String fieldName)
    {
        // Nó trống và không có lỗi nào được báo cáo. Trả về null trực tiếp
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(fieldName, "fieldName can't be blank");
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass())
        {
            try
            {
                Field field = superClass.getDeclaredField(fieldName);
                makeAccessible(field);
                return field;
            }
            catch (NoSuchFieldException e)
            {
                continue;
            }
        }
        return null;
    }

    /**
     *Lặp lại phép biến đổi hướng lên, lấy DeclaredMethod của đối tượng và buộc nó có thể truy cập được.
     * Nếu bạn vẫn không thể tìm thấy đối tượng sau khi chuyển đổi hướng lên, hãy trả về null.
     * Ghép tên hàm + kiểu tham số.
     * Được sử dụng khi một phương thức cần được gọi nhiều lần. Sử dụng hàm này để nhận Phương thức trước, sau đó gọi Method.invoke (Object obj, Object ... args)
     */
    public static Method getAccessibleMethod(final Object obj, final String methodName,
            final Class<?>... parameterTypes)
    {
        //Nó trống và không có lỗi nào được báo cáo. Trả về null trực tiếp
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(methodName, "methodName can't be blank");
        for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass())
        {
            try
            {
                Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
                makeAccessible(method);
                return method;
            }
            catch (NoSuchMethodException e)
            {
                continue;
            }
        }
        return null;
    }

    /**
     * Lặp lại phép biến đổi hướng lên, lấy DeclaredMethod của đối tượng và buộc nó có thể truy cập được.
     * Nếu bạn vẫn không thể tìm thấy đối tượng sau khi chuyển đổi hướng lên, hãy trả về null.
     * Chỉ khớp với tên chức năng.
     * Được sử dụng khi một phương thức cần được gọi nhiều lần. Sử dụng hàm này để nhận Phương thức trước, sau đó gọi Method.invoke (Object obj, Object ... args)
     */
    public static Method getAccessibleMethodByName(final Object obj, final String methodName, int argsNum)
    {
        // It is empty and no error is reported. Return null directly
        if (obj == null)
        {
            return null;
        }
        Validate.notBlank(methodName, "methodName can't be blank");
        for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass())
        {
            Method[] methods = searchType.getDeclaredMethods();
            for (Method method : methods)
            {
                if (method.getName().equals(methodName) && method.getParameterTypes().length == argsNum)
                {
                    makeAccessible(method);
                    return method;
                }
            }
        }
        return null;
    }

    /**
     * Thay đổi phương thức riêng tư / được bảo vệ thành công khai, cố gắng không gọi câu lệnh đã thay đổi thực tế, để tránh SecurityManager của JDK phàn nàn.*/
    public static void makeAccessible(Method method)
    {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
                && !method.isAccessible())
        {
            method.setAccessible(true);
        }
    }

    /**
     *Thay đổi biến thành viên riêng tư / được bảo vệ thành công khai, cố gắng không gọi câu lệnh đã thay đổi thực tế, để tránh JDK SecurityManager phàn nàn.
     */
    public static void makeAccessible(Field field)
    {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
                || Modifier.isFinal(field.getModifiers())) && !field.isAccessible())
        {
            field.setAccessible(true);
        }
    }

    /**
     * Thông qua phản xạ, nhận kiểu của tham số chung được khai báo trong định nghĩa Lớp. Lưu ý rằng kiểu chung phải được xác định ở lớp cha
     * Nếu không thể tìm thấy, hãy quay lại Object.class.
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClassGenricType(final Class clazz)
    {
        return getClassGenricType(clazz, 0);
    }

    /**
     * Thông qua phản xạ, nhận kiểu của tham số chung của lớp cha được khai báo trong định nghĩa Lớp.
     * Nếu không thể tìm thấy, hãy quay lại Object.class.
     */
    public static Class getClassGenricType(final Class clazz, final int index)
    {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType))
        {
            logger.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0)
        {
            logger.debug("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class))
        {
            logger.debug(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    public static Class<?> getUserClass(Object instance)
    {
        if (instance == null)
        {
            throw new RuntimeException("Instance must not be null");
        }
        Class clazz = instance.getClass();
        if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR))
        {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !Object.class.equals(superClass))
            {
                return superClass;
            }
        }
        return clazz;

    }

    /**
      *Chuyển đổi ngoại lệ đã kiểm tra trong quá trình phản chiếu thành ngoại lệ không kiểm tra.
      */
    public static RuntimeException convertReflectionExceptionToUnchecked(String msg, Exception e)
    {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException)
        {
            return new IllegalArgumentException(msg, e);
        }
        else if (e instanceof InvocationTargetException)
        {
            return new RuntimeException(msg, ((InvocationTargetException) e).getTargetException());
        }
        return new RuntimeException(msg, e);
    }
}
