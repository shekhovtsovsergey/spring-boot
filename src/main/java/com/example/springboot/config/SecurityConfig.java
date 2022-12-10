package com.example.springboot.config;


import com.example.springboot.service.UserServiceWeb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceWeb userServiceweb;
    private static final String USER_ENDPOINT = "/user/**";
    private static final String PRODUCT_ENDPOINT = "/product/**";


    public SecurityConfig(UserServiceWeb userServiceweb) {
        this.userServiceweb = userServiceweb;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(PRODUCT_ENDPOINT).hasRole("ADMIN")
                .antMatchers(PRODUCT_ENDPOINT).hasRole("MANAGER")
                .and()
                .formLogin();
    }

    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userServiceweb);
        return authenticationProvider;
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


}
