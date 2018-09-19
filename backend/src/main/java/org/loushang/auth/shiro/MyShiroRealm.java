package org.loushang.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.inspur.viz.user.data.RoleInfo;
import com.inspur.viz.user.data.UserInfo;
import com.inspur.viz.user.service.IUserInfoService;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserInfoService userInfoService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		for (RoleInfo role : userInfo.getRoleList()) {
			authorizationInfo.addRole(role.getCode());
			authorizationInfo.addStringPermission("role:*");
		}
		return authorizationInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String userId = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo userInfo = userInfoService.getUserInfoById(userId);
		if (userInfo == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
				userInfo.getPassword(), // 密码
				getName() // realm name
		);
		return authenticationInfo;
	}

}
