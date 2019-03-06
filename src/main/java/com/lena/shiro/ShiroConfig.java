package com.lena.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {
	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//设置登录页面
		//shiroFilterFactoryBean.setLoginUrl("/tologin");
		//设置登录成功跳转的页面
		shiroFilterFactoryBean.setSuccessUrl("test");
		//设置未授权跳转的页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
		
		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		/*filterChainDefinitionMap.put("/add", "authc");
		filterChainDefinitionMap.put("/update", "authc");*/
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/test", "authc");
		filterChainDefinitionMap.put("/add","perms[user:add]");
		filterChainDefinitionMap.put("/update","perms[user:update]");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		//需要登录访问的资源 , 一般将/**放在最下边
		//filterChainDefinitionMap.put("/**", "authc");

		return shiroFilterFactoryBean;
		
	}
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		//指定加密方式为MD5
		credentialsMatcher.setHashAlgorithmName("MD5");
		//加密次数
		credentialsMatcher.setHashIterations(2);
		//默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	/**
	 * 创建Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getUserRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
		UserRealm userRealm = new UserRealm();
		userRealm.setAuthorizationCachingEnabled(false);
		userRealm.setCredentialsMatcher(matcher);
		return new UserRealm();
	}
	/**
	 * thymeleaf的配置方言
	 * @return
	 */
	@Bean
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}
}
