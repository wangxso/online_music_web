package com.demo.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * 常用过滤器
         *  anon:无需验证
         *  authc:必须认证才能访问
         *  user:若使用remeberme可以直接使用
         *  perms:该资源必须授予资源权限才能访问
         *  role:该资源必须得到权限角色才能访问
         */
        Map<String,String> filterMap=new LinkedHashMap<>();
        //filterMap.put("/comment/**","authc");
        //filterMap.put("/musicDetail","authc");
        filterMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改登录页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        return  shiroFilterFactoryBean;
    }

    /**
     * 创建defaultWebSecurityManager
     *
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;

    }

    /**
     * 创建realm
     */
    @Bean("userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


    public static void main(String[] args) {
        //盐值用的用的是对用户名的加密（测试用的"lisi"）
        ByteSource credentialsSalt01 = ByteSource.Util.bytes("lisi");
        Object salt = null;//盐值
        Object credential = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        //1024指的是加密的次数
        Object simpleHash = new SimpleHash(hashAlgorithmName, credential,
                credentialsSalt01, 1024);
        System.out.println("加密后的值----->" + simpleHash);
    }
}
