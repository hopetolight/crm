package chenbo.work.crm.configuration.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className ShiroConfig
 * @authtor ChenBo
 * @date 2019/5/27
 */
@SpringBootConfiguration
@EnableConfigurationProperties(ShiroProperties.class)
@Slf4j
public class ShiroConfig {

    @Autowired
    private ShiroProperties properties;


    /**
     *
     * @author; ChenBo
     * @datetime: 2019/5/27
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1); //散列的次数，比如散列两次，相当于md5("");
        return hashedCredentialsMatcher;
    }


    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }


    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(properties.getRedisHost());
        redisManager.setPort(properties.getRedisPort());
        redisManager.setTimeout(properties.getRedisTimeout());
        if (StringUtils.isNotBlank(properties.getRedisPassword())) redisManager.setPassword(properties.getRedisPassword());
        return redisManager;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public SessionManager sessionManager(){
        SessionManagerImpl sessionManager = new SessionManagerImpl();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }


    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("auth/login","logout");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setLoginUrl("/auth/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
    * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager  securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
    *
    * @author; ChenBo
    * @datetime: 2019/5/27
    */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver(){
        return new ExceptionHandlerManager();
    }


}
