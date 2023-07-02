package com.qlcx.common.utils.spring;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * lớp công cụ mùa xuân để tạo điều kiện lấy đậu trong môi trường quản lý không dùng lò xo
 */
@Component
public final class SpringUtils implements BeanFactoryPostProcessor
{
    /** Bối cảnh ứng dụng mùa xuân*/
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * Nhận đối tượng
     *
     * @param Tên 
     * @return Object Một thể hiện của bean được đăng ký với tên đã cho
     * @throws org.springframework.beans.BeansException
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException
    {
        return (T) beanFactory.getBean(name);
    }

    /**
     * Nhận một đối tượng thuộc loại yêu cầu
     *
     * @param clz
     * @return
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }

    /**
     * Nếu BeanFactory chứa định nghĩa bean khớp với tên đã cho, thì trả về true
     *
     * @param tên
     * @return boolean
     */
    public static boolean containsBean(String name)
    {
        return beanFactory.containsBean(name);
    }

    /**
     * Xác định xem một định nghĩa bean được đăng ký với một tên nhất định là một singleton hay một nguyên mẫu. Nếu định nghĩa bean tương ứng với tên đã cho không được tìm thấy, một ngoại lệ sẽ được ném ra (NoSuchBeanDefinitionException)
     *
     * @param Tên 
     * @return boolean
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     *
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return beanFactory.isSingleton(name);
    }

    /**
     * @param Tên
     * @return Class Loại đối tượng đã đăng ký
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     *
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return beanFactory.getType(name);
    }

    /**
     * Nếu tên bean đã cho có các bí danh trong định nghĩa bean, các bí danh này sẽ được trả về
     *
     * @param tên
     * @return
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     *
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException
    {
        return beanFactory.getAliases(name);
    }

    /**
     * Nhận đối tượng proxy aop
     *
     * Kẻ xâm lược @param
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker)
    {
        return (T) AopContext.currentProxy();
    }
}
