package com.manning.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReaderRepository readerRepository;

    /* 覆盖的第 1 个configure */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/").access("hasRole('READER')")   /* 说明 > "/"{ReadingListController方法映射此路径} 路径的请求只有经过身份认证并且拥有 READER 角色的用户才能访问 */
            .antMatchers("/**").permitAll()                          /* 说明 > 其他的路径请求向所有用户开放 */
            .and()
            .formLogin()
            .loginPage("/login")                /* 设置 > 登陆表单的路径 */
            .failureUrl("/login?error=true");   /* 设置 > 登陆失败页，重新定向至 "/login" */
    }

    /* 覆盖的第 2 个configure */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         *  设置了一个自定义的 UserDetailsService : 该服务可以是任意实现UserDetailsService的类，用于查找指定用户名的用户
         */
        auth.userDetailsService(new UserDetailsService() {      /* 匿名内部类方式实现 */
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    return readerRepository.findOne(username);
                }
            });
        }
}