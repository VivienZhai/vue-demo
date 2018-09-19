package org.loushang.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.loushang.auth.shiro.MyShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/home");

		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;

	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}
}
