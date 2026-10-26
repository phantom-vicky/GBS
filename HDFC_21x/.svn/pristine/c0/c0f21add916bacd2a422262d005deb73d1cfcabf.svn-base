package com.isl.gbm.user.management.security.shiro.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.Filter;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.shiro.web.servlet.Cookie.SameSiteOptions.STRICT;

@Configuration
public class ShiroConfig {

    @Value("${shiro.autheciation.realm.type:JDBC}")
    private String autheciationType;

    @Autowired
    @Qualifier("customShiroListener")
    private SessionListener customSessionListener;

    @Autowired
    @Lazy
    private BcryptPasswordMatcher bcryptPasswordMatcher;

    @Autowired
    private CustomLdapRealm customLdapRealm;

    @Bean
    public org.apache.shiro.cache.CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
        dao.setActiveSessionsCacheName("shiro-activeSessionCache");
        return dao;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager mgr = new DefaultWebSessionManager();
        mgr.setSessionDAO(sessionDAO());
        mgr.setGlobalSessionTimeout(30 * 60_000); // 30 min
        mgr.setSessionValidationInterval(15 * 60_000); // 15 min (50% of session timeout)
        mgr.getSessionIdCookie().setSameSite(STRICT);
        mgr.getSessionIdCookie().setHttpOnly(true);
        mgr.getSessionIdCookie().setSecure(true);
        mgr.getSessionListeners().add(customSessionListener);
        return mgr;
    }

    /** ---------------- Remember-Me ---------------- */
    private SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3_600_000); // 1h
        cookie.setSameSite(STRICT);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager mgr = new CookieRememberMeManager();
        mgr.setCookie(rememberMeCookie());
        mgr.setCipherKey(Base64.getDecoder().decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return mgr;
    }

    @Bean
    @Lazy
    @DependsOn("lifecycleBeanPostProcessor")
    public CustomRealm jdbcRealm() {
        CustomRealm realm = new CustomRealm();
        realm.setCachingEnabled(true);
        realm.setCredentialsMatcher(bcryptPasswordMatcher);
        realm.init();
        return realm;
    }

    @Bean(name = "shiroSecurityManger")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager mgr = new DefaultWebSecurityManager();
        if ("AD".equalsIgnoreCase(autheciationType)) {
            mgr.setRealm(customLdapRealm);
        } else {
            mgr.setRealm(jdbcRealm());
        }

        mgr.setRememberMeManager(rememberMeManager());
        mgr.setSessionManager(sessionManager());
        mgr.setCacheManager(shiroCacheManager());
        return mgr;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowCredentials(true);
        cfg.addAllowedOriginPattern("*"); // adapt to your front-end domain
        cfg.addAllowedHeader("*");
        cfg.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return new CorsFilter(src);
    }


    @Bean(name = "customCsrfTokenRepository")
    public CsrfTokenRepository getCustomCsrfTokenRepository() {
        return new ShiroSessionCsrfTokenRepository();
    }

    @Bean
    public Filter customFilter() {
        return new CustomFilter();
    }

    @Autowired
    private ShiroCsrfFilter shiroCsrfFilter;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("shiroSecurityManger") DefaultWebSecurityManager securityManager, @Qualifier("customCsrfTokenRepository") CsrfTokenRepository csrfTokenRepository) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");

        Map<String, Filter> filters = new HashMap<>();
//        filters.put("cors", corsFilter());
        filters.put("shiroCsrf", shiroCsrfFilter);
        filters.put("customFilter", customFilter());
        bean.setFilters(filters);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/index.html", "anon");
        map.put("/favicon.ico", "anon");
        map.put("/assets/**", "anon");
        map.put("/login", "anon");
        map.put("/logout", "anon");
        map.put("/gbmultra/user/login", "anon");
        map.put("/gbmultra/isac/api/**", "anon");
        map.put("/app/login/**", "anon");
        map.put("/", "anon");
        map.put("/**", "anon OPTIONS");
        map.put("/api/**", "authc,shiroCsrf");
        map.put("/gbmultra/**", "authc,shiroCsrf");
        map.put("/**", "authc,shiroCsrf");

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/index.html", "anon");
        chainDefinition.addPathDefinition("/favicon.ico", "anon");
        chainDefinition.addPathDefinition("/assets/**", "anon");
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/logout", "anon");
        chainDefinition.addPathDefinition("/gbmultra/user/login", "anon");
        chainDefinition.addPathDefinition("/app/login/**", "anon");
        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/api/**", "authc,shiroCsrf");
        chainDefinition.addPathDefinition("/gbmultra/**", "authc,shiroCsrf");
        chainDefinition.addPathDefinition("/**", "authc,shiroCsrf");
        return chainDefinition;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator a = new DefaultAdvisorAutoProxyCreator();
        a.setProxyTargetClass(true);
        return a;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("shiroSecurityManger") DefaultWebSecurityManager mgr) {
        AuthorizationAttributeSourceAdvisor adv = new AuthorizationAttributeSourceAdvisor();
        adv.setSecurityManager(mgr);
        return adv;
    }
}