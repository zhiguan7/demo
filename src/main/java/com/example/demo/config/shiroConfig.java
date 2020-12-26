package com.example.demo.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("des") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加过滤器，拦截
        HashMap<String,String> filterChainDefinitionMap = new HashMap<>();

        filterChainDefinitionMap .put("/index","anon");

        //授权
        filterChainDefinitionMap .put("/human/*","authc");

        filterChainDefinitionMap .put("/logout","logout");

        filterChainDefinitionMap .put("/human/add","perms[1]");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap );

        //设置登陆请求
        bean.setLoginUrl("/toLogin");

        return bean;
    }

    @Bean(name = "des")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("hr") HumanRealm humanRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(humanRealm);
        return  securityManager;
    }

    @Bean(name="hr")
    public HumanRealm humanRealm(HashedCredentialsMatcher matcher){
        HumanRealm humanRealm = new HumanRealm();
        humanRealm.setAuthorizationCachingEnabled(false);
        humanRealm.setCredentialsMatcher(matcher);
        return humanRealm;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-512");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}

