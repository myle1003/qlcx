package com.qlcx.common.utils.bean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Công cụ đậu
 */
public class BeanUtils extends org.springframework.beans.BeanUtils
{
    /** Chỉ mục bắt đầu của tên thuộc tính trong tên phương thức bean */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * Phương thức getter đối sánh biểu thức chính quy */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * Phương thức setter đối sánh biểu thức chính quy */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Phương pháp công cụ sao chép thuộc tính Bean.
     *
     * @param đối tượng đích cuối cùng
     * @param src đối tượng nguồn
     */
    public static void copyBeanProp(Object dest, Object src)
    {
        try
        {
            copyProperties(src, dest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Lấy phương thức setter của đối tượng.
     *
     * @param Đối tượng  obj
     * @return Danh sách các phương thức setter cho các đối tượng 
     */
    public static List<Method> getSetterMethods(Object obj)
    {
        // Danh sách các phương pháp setter
        List<Method> setterMethods = new ArrayList<Method>();

        // Nhận tất cả các phương pháp
        Method[] methods = obj.getClass().getMethods();

        // Tìm phương pháp setter

        for (Method method: methods)
        {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1))
            {
                setterMethods.add(method);
            }
        }
        // trả về danh sách phương thức setter
        return setterMethods;
    }

    /**
     * Phương thức Getter của đối tượng.
     *
     * @param Đối tượng  obj
     * Danh sách các phương thức getter của đối tượng @return
     */

    public static List<Method> getGetterMethods(Object obj)
    {
        // Danh sách các phương pháp getter
        List<Method> getterMethods = new ArrayList<Method>();
        // Nhận tất cả các phương pháp
        Method[] methods = obj.getClass().getMethods();
        // Tìm phương pháp getter
        for (Method method: methods)
        {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0))
            {
                getterMethods.add(method);
            }
        }
        // Trả lại danh sách các phương thức getter
        return getterMethods;
    }

    /**
     * Kiểm tra xem các tên thuộc tính trong tên phương thức Bean có bằng nhau không. <br>
     * Vì tên thuộc tính getName () và setName () giống nhau, tên thuộc tính getName () và setAge () không giống nhau.
     *
     * @param m1 tên phương thức 1
     * @param m2 tên phương thức 2
     * @return trả về true như tên thuộc tính, nếu không trả về false
     */

    public static boolean isMethodPropEquals(String m1, String m2)
    {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
