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
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;



public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UsersService usersService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑，，，");
		SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
		
		Subject subject = SecurityUtils.getSubject();
		Users principal = (Users)subject.getPrincipal();
		info.addStringPermission(principal.getPrivilege());

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑，，，");
		//token携带了用户信息
		UsernamePasswordToken token=(UsernamePasswordToken) arg0;
		//从UsernamePasswordToken 中来获取username
		//调用数据库的方法，从数据库中查询username对应的用户记录
		Users user = usersService.findUserByName(token.getUsername());
		System.out.println(token.getPassword());
		if(user==null){
			// 若用户不存在，则可以抛出 UnknownAccoountException 异常
			return null;
		}
		//盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes("lena");
		System.out.println(user.getPassword());
		//封装用户信息，构建AuthenticationInfo对象并返回
		return new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,"");
	}

}
