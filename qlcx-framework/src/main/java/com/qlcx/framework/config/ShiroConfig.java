package com.qlcx.framework.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qlcx.common.utils.StringUtils;
import com.qlcx.common.utils.spring.SpringUtils;
import com.qlcx.framework.shiro.realm.UserRealm;
import com.qlcx.framework.shiro.session.OnlineSessionDAO;
import com.qlcx.framework.shiro.session.OnlineSessionFactory;
import com.qlcx.framework.shiro.web.filter.LogoutFilter;
import com.qlcx.framework.shiro.web.filter.captcha.CaptchaValidateFilter;
import com.qlcx.framework.shiro.web.filter.kickout.KickoutSessionFilter;
import com.qlcx.framework.shiro.web.filter.online.OnlineSessionFilter;
import com.qlcx.framework.shiro.web.filter.sync.SyncOnlineSessionFilter;
import com.qlcx.framework.shiro.web.session.OnlineWebSessionManager;
import com.qlcx.framework.shiro.web.session.SpringSessionValidationScheduler;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * Permission configuration loading
 */
@Configuration
public class ShiroConfig
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    // Session timeout time in milliseconds (default 30 minutes)
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    // How often to check the validity of a session, in milliseconds, the default is 10 minutes
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    // Maximum sessions of the same user
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    // kick out previously logged in/post logged in users, kick out previously logged in users by default
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    // Verification code switch
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    // Type of verification code
    @Value("${shiro.user.captchaType}")
    private String captchaType;

    // Set the domain name of the cookie
    @Value("${shiro.cookie.domain}")
    private String domain;

    // Set the effective access path of the cookie
    @Value("${shiro.cookie.path}")
    private String path;

    // Set HttpOnly attribute
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    // Set the cookie expiration time, in seconds
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    // Login address
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    // Permission authentication failed address
    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * Cache manager using Ehcache
     */
    @Bean
    public EhCacheManager getEhCacheManager()
    {
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("qlcx");
        EhCacheManager em = new EhCacheManager();
        if (StringUtils.isNull(cacheManager))
        {
            em.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
            return em;
        }
        else
        {
            em.setCacheManager(cacheManager);
            return em;
        }
    }

   /**
     * Return to the configuration file flow to prevent the ehcache configuration file from being occupied all the time, and the project cannot be completely destroyed and redeployed
    */
    protected InputStream getCacheManagerConfigFileInputStream()
    {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try
        {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        }
        catch (IOException e)
        {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * Custom Realm
     */
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManager)
    {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    /**
     * Custom sessionDAO session
     */
    @Bean
    public OnlineSessionDAO sessionDAO()
    {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }

    /**
     * Custom sessionFactory session
     */
    @Bean
    public OnlineSessionFactory sessionFactory()
    {
        OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
        return sessionFactory;
    }

    /**
     * Session Manager
     */
    @Bean
    public OnlineWebSessionManager sessionManager()
    {
        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        // Join the cache manager
        manager.setCacheManager(getEhCacheManager());
        // Delete expired session
        manager.setDeleteInvalidSessions(true);
        // Set the global session timeout
        manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        // remove JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // Define the invalid session timing scheduler to be used
        manager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        // Check the session regularly
        manager.setSessionValidationSchedulerEnabled(true);
        // Custom SessionDao
        manager.setSessionDAO(sessionDAO());
        // Custom sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * Security Manager
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // Set realm.
        securityManager.setRealm(userRealm);
        // remember me
        securityManager.setRememberMeManager(rememberMeManager());
        // Inject into the cache manager
        securityManager.setCacheManager(getEhCacheManager());
        // session manager
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Exit filter
     */
    public LogoutFilter logoutFilter()
    {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setCacheManager(getEhCacheManager());
        logoutFilter.setLoginUrl(loginUrl);
        return logoutFilter;
    }

    /**
     * Shiro filter configuration
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro's core security interface, this attribute is required
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // If the authentication fails, jump to the configuration of the login page
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // Permission authentication fails, jump to the specified page
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro connection constraint configuration, namely the definition of filter chain
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // Set anonymous access to static resources
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/qlcx.png**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/qlcx/**", "anon");
        filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
        // logout address, shiro to clear the session
        filterChainDefinitionMap.put("/logout", "logout");
        // Access that does not need to be intercepted
        filterChainDefinitionMap.put("/login", "anon,captchaValidate");
        // Registration related
        filterChainDefinitionMap.put("/register", "anon,captchaValidate");
        
     // Publish Image
        filterChainDefinitionMap.put("/profile/upload/**", "anon");
        filterChainDefinitionMap.put("/profile/thumb/upload/**", "anon");
        // System permission list
        // filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll())

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("onlineSession", onlineSessionFilter());
        filters.put("syncOnlineSession", syncOnlineSessionFilter());
        filters.put("captchaValidate", captchaValidateFilter());
        filters.put("kickout", kickoutSessionFilter());
        // Log off successfully, then jump to the specified page
        filters.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // All requests require authentication
        filterChainDefinitionMap.put("/**", "user, kickout, onlineSession, syncOnlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * Customize online user processing filters
     */
    @Bean
    public OnlineSessionFilter onlineSessionFilter()
    {
        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setLoginUrl(loginUrl);
        return onlineSessionFilter;
    }

    /**
     * Custom online user synchronization filter
     */
    @Bean
    public SyncOnlineSessionFilter syncOnlineSessionFilter()
    {
        SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
        return syncOnlineSessionFilter;
    }

    /**
     * Custom verification code filter
     */
    @Bean
    public CaptchaValidateFilter captchaValidateFilter()
    {
        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }

    /**
     * Cookie attribute setting
     */
    public SimpleCookie rememberMeCookie()
    {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * remember me
     */
    public CookieRememberMeManager rememberMeManager()
    {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    /**
     * Multiple user login restrictions for the same user
     */
    public KickoutSessionFilter kickoutSessionFilter()
    {
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        kickoutSessionFilter.setCacheManager(getEhCacheManager());
        kickoutSessionFilter.setSessionManager(sessionManager());
        // The maximum number of sessions of the same user, the default is -1 unlimited; for example, 2 means that the same user is allowed to log in at most two people at the same time
        kickoutSessionFilter.setMaxSession(maxSession);
        // Whether to kick out the later login, the default is false; that is, the latter login user kicks out the former login user; kickout order
        kickoutSessionFilter.setKickoutAfter(kickoutAfter);
        // The address to redirect to after being kicked out
        kickoutSessionFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionFilter;
    }

    /**
     * Integration of thymeleaf template engine and shiro framework
     */
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }

    /**
     * Open Shiro annotation notifier
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
