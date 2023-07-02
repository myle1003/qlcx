package com.qlcx.common.utils;

import java.util.Iterator;
import java.util.Set;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlcx.common.utils.spring.SpringUtils;

/**
 * Lớp công cụ bộ nhớ đệm
 */
public class CacheUtils
{
    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    private static CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);

    private static final String SYS_CACHE = "sys-cache";

    /**
     * Lớp công cụ bộ nhớ đệm
     *
     * @param key
     * @return
     */
    public static Object get(String key)
    {
        return get(SYS_CACHE, key);
    }

    /**
     * Nhận bộ nhớ cache SYS_CACHE
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key, Object defaultValue)
    {
        Object value = get(key);
        return value != null? value: defaultValue;
    }

    /**
     * Ghi vào bộ nhớ cache SYS_CACHE
     *
     * @param khóa
     * @return
     */
    public static void put(String key, Object value)
    {
        put(SYS_CACHE, key, value);
    }

    /**
     * Xóa khỏi bộ nhớ cache SYS_CACHE
     *
     * @param key
     * @return
     */
    public static void remove(String key)
    {
        remove(SYS_CACHE, key);
    }

    /**
     * Nhận bộ nhớ cache
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key)
    {
        return getCache(cacheName).get(getKey(key));
    }

    /**
     * Nhận bộ nhớ cache
     *
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String cacheName, String key, Object defaultValue)
    {
        Object value = get(cacheName, getKey(key));
        return value != null? value: defaultValue;
    }

    /**
     * Nhận bộ nhớ cache
     *
     * @param cacheName
     * @param Chìa khóa
     * @param Giá trị 
     */
    public static void put(String cacheName, String key, Object value)
    {
        getCache(cacheName).put(getKey(key), value);
    }

    /**
     * Xóa khỏi bộ nhớ cache
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key)
    {
        getCache(cacheName).remove(getKey(key));
    }

    /**
     * Xóa tất cả khỏi bộ nhớ cache
     *
     * @param cacheName
     */
    public static void removeAll(String cacheName)
    {
        Cache<String, Object> cache = getCache(cacheName);
        Set<String> keys = cache.keys();
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            cache.remove(it.next());
        }
        logger.info("Clear cache: {} => {}", cacheName, keys);
    }

    /**
     * Xóa khóa được chỉ định khỏi bộ nhớ cache
     *
     * @param keys
     */
    public static void removeByKeys(Set<String> keys)
    {
        removeByKeys(SYS_CACHE, keys);
    }

    /**
     * Xóa khóa được chỉ định khỏi bộ nhớ cache
     *
     * @param cacheName
     * @param keys
     */
    public static void removeByKeys(String cacheName, Set<String> keys)
    {
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            remove(it.next());
        }
        logger.info("Clear cache: {} => {}", cacheName, keys);
    }

    /**
     * Nhận tên khóa bộ nhớ cache
     *
     * @param key
     * @return
     */
    private static String getKey(String key)
    {
        return key;
    }

    /**
     * Lấy bộ nhớ cache và hiển thị nhật ký nếu không.
     *
     * @param cacheName
     * @return
     */
    private static Cache<String, Object> getCache(String cacheName)
    {
        Cache<String, Object> cache = cacheManager.getCache(cacheName);
        if (cache == null)
        {
            throw new RuntimeException("The cache " + cacheName + " is not defined in the current system.");
        }
        return cache;
    }

}
