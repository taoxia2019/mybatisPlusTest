package com.lena.shiro;

import com.lena.pojo.Users;
import com.lena.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;



public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UsersService usersService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑，，，");
		SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
		
		/*Subject subject = SecurityUtils.getSubject();
		Users principal = (Users)subject.getPrincipal();*/
		
		//info.addStringPermission(principal.getPrivilege());
		info.addStringPermission("user:add");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑，，，");
		UsernamePasswordToken token=(UsernamePasswordToken) arg0;
		Users user = usersService.findUserByName(token.getUsername());

		if(user==null){
			return null;
		}
		
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
	}

}
