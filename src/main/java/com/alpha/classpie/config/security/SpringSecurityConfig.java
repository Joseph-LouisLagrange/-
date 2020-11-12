package com.alpha.classpie.config.security;

import com.alpha.classpie.pojo.user.User;
import com.alpha.classpie.service.inf.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled =true , securedEnabled = true)
@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "globalPermissionEvaluator" , type = PermissionEvaluator.class)
    PermissionEvaluator permissionEvaluator;

    @Resource(name = "smsCodeAuthenticationSecurityConfig")
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> smsCodeAuthenticationSecurityConfig;

    @Resource(name = "defaultUserDetails")
    UserDetailsService userService;

    @Resource(name = "defaultUserService")
    UserService<User> simpleUserService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.debug(true);
        web.expressionHandler(getMethodSecurityExpressionHandler());
    }

    public SecurityExpressionHandler<FilterInvocation> getMethodSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler securityExpressionHandler=new DefaultWebSecurityExpressionHandler();
        //securityExpressionHandler.setDefaultRolePrefix("");
        securityExpressionHandler.setPermissionEvaluator(permissionEvaluator);
        return securityExpressionHandler;
    }


    /**
     * 设置JWT的拦截器
     * @return JWTAuthenticationFilter
     * @throws Exception
     */
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/jwtLogin"));
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new ForwardAuthenticationSuccessHandler("/user/loginSuccess"));
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new ForwardAuthenticationFailureHandler("/user/loginFail"));
        return jwtAuthenticationFilter;
    }

    JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager());
        jwtAuthorizationFilter.setUserService(simpleUserService);
        return jwtAuthorizationFilter;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(smsCodeAuthenticationSecurityConfig);
        http.csrf().disable();
        http.headers().frameOptions().and().contentTypeOptions();
        http.cors().configurationSource(corsConfigurationSource());
        // 添加JWT登录拦截器
        http.addFilter(jwtAuthenticationFilter())
               // 添加JWT鉴权拦截器
                .addFilter(jwtAuthorizationFilter())
                .sessionManagement()
                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/smsLogin","/user/usernamePasswordLoginProcess","/user/loginFail").anonymous()
                .antMatchers("/user/loginSuccess").fullyAuthenticated();
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Resource(name = "defaultPasswordEncoder")
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder);
    }


    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
