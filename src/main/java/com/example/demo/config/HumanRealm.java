package com.example.demo.config;

import com.example.demo.pojo.Human;
import com.example.demo.service.HumanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Security;

public class HumanRealm extends AuthorizingRealm {

    @Autowired
    private HumanService humanService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————————————授权————————————");

        SimpleAuthorizationInfo Info = new SimpleAuthorizationInfo();

//        Info.addStringPermission("1");

        Subject subject = SecurityUtils.getSubject();

        Human principal = (Human) subject.getPrincipal();

        Info.addStringPermission(""+principal.getPerms());

        return Info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————————————认证————————————");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        Human human = humanService.queryByName(token.getUsername());

        if(human==null) return null;


        ByteSource salt = ByteSource.Util.bytes(human.getName());
        //new SimpleHash("MD5",human.getPassword(),human.getName(),1024).toHex() 就是加密了，一般放在注册或者更改密码
//        String s = new SimpleHash("MD5",token.getPassword(),salt,1024).toHex();
//        System.out.println(s);

        return new SimpleAuthenticationInfo(human,human.getPassword(),salt,this.getName());
    }
}
