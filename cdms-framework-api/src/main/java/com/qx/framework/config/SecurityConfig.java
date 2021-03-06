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
 * spring security??????
 * 
 * @author suhp
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig
{

    /**
     * ????????????????????????. ????????????
     */
    @Configuration
    @Order(1)
    static class AdminConfigurerAdapter extends WebSecurityConfigurerAdapter {
        /**
         * ???????????????????????????
         */
        @Autowired
        @Qualifier("UserDetailsServiceImpl")
        private UserDetailsService userDetailsService;
        /**
         * ???????????????
         */
        @Autowired
        private LogoutSuccessHandlerImpl logoutSuccessHandler;

        /**
         * token???????????????
         */
        @Autowired
        private JwtAuthenticationTokenFilter authenticationTokenFilter;
        /**
         * ???????????????????????????
         */
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder()
        {
            return new BCryptPasswordEncoder();
        }

        /**
         * ?????????????????????
         */
        @Autowired
        private AuthenticationEntryPointImpl unauthorizedHandler;



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

//            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//            //???????????????????????????
//            daoAuthenticationProvider.setUserDetailsService(new UserDetailsService() {
//                @Override
//                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                    // ????????????
//                    return null;
//                }
//            });
//            // ????????????????????????????????????
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//            auth.authenticationProvider(daoAuthenticationProvider);
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // ????????????????????????
            http.antMatcher("/admin")
                    // CRSF????????????????????????session
                    .csrf().disable()
                    // ?????????????????????
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // ??????token??????????????????session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    // ????????????
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
            //??????????????????
            http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
            //token?????????
            http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        }
    }

    /**
     * ???????????????????????????. ??????{@link Order}???????????????????????????
     */
    @Configuration
    static class ClientConfigurerAdapter extends WebSecurityConfigurerAdapter {

        /**
         * ???????????????????????????
         */
        @Autowired
        @Qualifier("ClientUserDetailsServiceImpl")
        private UserDetailsService userDetailsService;
        /**
         * ???????????????
         */
        @Autowired
        private ClientLogoutSuccessHandlerImpl logoutSuccessHandler;

        /**
         * token???????????????
         */
        @Autowired
        private ClientJwtAuthenticationTokenFilter authenticationTokenFilter;
        /**
         * ???????????????????????????
         */
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder()
        {
            return new BCryptPasswordEncoder();
        }
        /**
         * ?????????????????????
         */
        @Autowired
        private AuthenticationEntryPointImpl unauthorizedHandler;



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // ????????????????????????
            http.antMatcher("/client")
                    // CRSF????????????????????????session
                    .csrf().disable()
                    // ?????????????????????
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    // ??????token??????????????????session
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    // ????????????
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
