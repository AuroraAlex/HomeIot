package com.cuit.homeiot.config;

import com.cuit.homeiot.component.MyAuthenticationFailureHandler;
import com.cuit.homeiot.component.MyAuthenticationSuccessHandler;
import com.cuit.homeiot.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;


@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final CustomUserService customUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SpringSecurityConfig(MyAuthenticationSuccessHandler myAuthenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, CustomUserService customUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.myAuthenticationSuccessHandler = myAuthenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.customUserService = customUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.js", "/**/*.json", "/**/*.css", "/**/*.js", "/**/*.map",
//                "/**/*.html",
                "/**/*.svg",
                "/**/*.png");
    }

    //    授权规则配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/ui/login.html").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/failed").permitAll();
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .loginPage("/ui/login.html").loginProcessingUrl("/login")
                .and()
//                同源下可以访问 解决iframe无法显示问题
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .logout().permitAll();
        http.csrf().disable();
    }


//    认证配置
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        可以通过jdbc、内存添加角色 使用and拼接
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("admin"))
//                .roles("admin").and()
//                .withUser("user")
//                .password(new BCryptPasswordEncoder().encode("user"))
//                .roles("user");
        auth.userDetailsService(customUserService).passwordEncoder(new Pbkdf2PasswordEncoder());
    }

}
