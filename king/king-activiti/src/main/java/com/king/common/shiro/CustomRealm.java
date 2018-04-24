package com.king.common.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.king.pojo.User;
import com.king.service.IUserService;

public class CustomRealm extends AuthorizingRealm {

    IUserService userService;

    

    public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	// 设置realm的名�?
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    //realm的认证方法，从数据库查询用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String loginName = (String) token.getPrincipal();

        // 第二步：根据用户输入的userCode从数据库查询
        User user = userService.findByLoginName(loginName);
        if (user == null) {
			throw new UnknownAccountException();//没找到帐号
		}

		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); //帐号锁定
		}

        //将activeUser设置simpleAuthenticationInfo 验证通过不通过抛异常
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user, user.getPassword(),ByteSource.Util.bytes(user.getCredentialsSalt()), this.getName());
        
       

        return simpleAuthenticationInfo;
    }



    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //principals获取主身份信息
        //将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
        User activeUser =  (User) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(activeUser.getLoginName()));
		authorizationInfo.setStringPermissions(userService.findPermissions(activeUser.getLoginName()));

        return authorizationInfo;
    }
    
    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        User user = (User) principals.getPrimaryPrincipal();
        removeUserCache(user);
    }
    /**
     * 清除用户缓存
     * @param shiroUser
     */
    public void removeUserCache(User shiroUser){
        removeUserCache(shiroUser.getLoginName());
    }
    
    /**
     * 清除用户缓存
     * @param loginName
     */
    public void removeUserCache(String loginName){
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
