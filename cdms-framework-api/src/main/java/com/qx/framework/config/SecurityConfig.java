package com.qx.framework.config;

import com.qx.common.core.domain.AjaxResult;
import com.qx.framework.security.LoginUser;
import com.qx.framework.security.filter.ClientJwtAuthenticationTokenFilter;
import com.qx.framework.security.filter.JwtAuthenticationTokenFilter;
import com.qx.framework.security.handle.*;
import com.qx.framework.security.service.TokenService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.qx.framework.security.filter.JwtAuthenticationTokenFilter;
import com.qx.framework.security.handle.AuthenticationEntryPointImpl;
import com.qx.framework.security.handle.LogoutSuccessHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring security配置
 * 
 * @author suhp
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig
{

    /**
     * 后台接口安全策略. 默认配置
     */
    @Configuration
    @Order(1)
    static class AdminConfigurerAdapter extends WebSecurityConfigurerAdapter {
        /**
         * 自定义用户认证逻辑
         */
        @Autowired
        @Qualifier("UserDetailsServiceImpl")
        private UserDetailsService userDetailsService;
        /**
         * 退出处理类
         */
        @Autowired
        private LogoutSuccessHandlerImpl logoutSuccessHandler;

        /**
         * token认证过滤器
         */
        @Autowired
        private JwtAuthenticationTokenFilter authenticationTokenFilter;
        /**
         * 强散列哈希加密实现
         */
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder()
        {
            return new BCryptPasswordEncoder();
        }

        /**
         * 认证失败处理类
         */
        @Autowired
        private AuthenticationEntryPointImpl unauthorizedHandler;



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

//            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//            //用户详情服务个性化
//            daoAuthenticationProvider.setUserDetailsService(new UserDetailsService() {
//                @Override
//                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                    // 自行实现
//                    return null;
//                }
//            });
//            // 也可以设计特定的密码策略
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//            auth.authenticationProvider(daoAuthenticationProvider);
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 根据需求自行定制
            http.antMatcher("/admin")
                    // CRSF禁用，因为不使用session
                    .csrf().disable()
                    // 认证失败处理类
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // 基于token，所以不需要session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    // 过滤请求
                    .authorizeRequests()
                    .antMatchers("/login", "/captchaImage").anonymous()
                    .antMatchers(
                            HttpMethod.GET,
                            "/*.html",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/**/*.png",
                            "/**/*.mp4",
                            "/**/*.jpg"
                    ).permitAll()
                    .antMatchers("/profile/**").anonymous()
                    .antMatchers("/common/download**").anonymous()
                    .antMatchers("/common/download/**").anonymous()
                    .antMatchers("/swagger-ui.html").anonymous()
                    .antMatchers("/swagger-resources/**").anonymous()
                    .antMatchers("/webjars/**").anonymous()
                    .antMatchers("/*/api-docs").anonymous()
                    .antMatchers("/druid/**").anonymous()
                    .anyRequest().authenticated()
                    .and()
                    .headers().frameOptions().disable();
            //退出登录回调
            http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
            //token过滤器
            http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        }
    }

    /**
     * 学生端接口安全策略. 没有{@link Order}注解优先级比上面低
     */
    @Configuration
    static class ClientConfigurerAdapter extends WebSecurityConfigurerAdapter {

        /**
         * 自定义用户认证逻辑
         */
        @Autowired
        @Qualifier("ClientUserDetailsServiceImpl")
        private UserDetailsService userDetailsService;
        /**
         * 退出处理类
         */
        @Autowired
        private ClientLogoutSuccessHandlerImpl logoutSuccessHandler;

        /**
         * token认证过滤器
         */
        @Autowired
        private ClientJwtAuthenticationTokenFilter authenticationTokenFilter;
        /**
         * 强散列哈希加密实现
         */
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
        /**
         * 认证失败处理类
         */
        @Autowired
        private AuthenticationEntryPointImpl unauthorizedHandler;



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 根据需求自行定制
            http.antMatcher("/client")
                    // CRSF禁用，因为不使用session
                    .csrf().disable()
                    // 认证失败处理类
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // 基于token，所以不需要session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    // 过滤请求
                    .authorizeRequests()
                    .antMatchers("/client/student/login").anonymous()
                    .antMatchers(
                            HttpMethod.GET,
                            "/*.html",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js",
                            "/**/*.png",
                            "/**/*.mp4",
                            "/**/*.jpg"
                    ).permitAll()
                    .antMatchers("/profile/**").anonymous()
                    .antMatchers("/common/download**").anonymous()
                    .antMatchers("/common/download/**").anonymous()
                    .antMatchers("/swagger-ui.html").anonymous()
                    .antMatchers("/swagger-resources/**").anonymous()
                    .antMatchers("/webjars/**").anonymous()
                    .antMatchers("/*/api-docs").anonymous()
                    .antMatchers("/druid/**").anonymous()
                    .anyRequest().authenticated()
                    .and()
                    .headers().frameOptions().disable();
            http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
            http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        }
    }

}
