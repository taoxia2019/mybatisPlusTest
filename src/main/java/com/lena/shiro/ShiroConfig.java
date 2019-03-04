package com.lena.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

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
		
		Map<String,String> filterChainDefinitionMap=new LinkedHashMap<String,String>();
		/*filterChainDefinitionMap.put("/add", "authc");
		filterChainDefinitionMap.put("/update", "authc");*/
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/test", "authc");
		filterChainDefinitionMap.put("/add","perms[user:add]");
		filterChainDefinitionMap.put("/update","perms[user:update]");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
		return shiroFilterFactoryBean;
		
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
	public UserRealm getUserRealm(){
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
