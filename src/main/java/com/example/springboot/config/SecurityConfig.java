package com.example.springboot.config;


import com.example.springboot.security.JwtRequestFilter;
import com.example.springboot.service.UserServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceWeb userServiceweb;
    private static final String USER_ENDPOINT = "/user/**";
    private static final String PRODUCT_ENDPOINT = "/product/**";

    @Autowired
    private JwtRequestFilter filter;

    public SecurityConfig(UserServiceWeb userServiceweb) {
        this.userServiceweb = userServiceweb;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(PRODUCT_ENDPOINT).hasRole("ADMIN")
                .antMatchers(PRODUCT_ENDPOINT).hasRole("MANAGER")
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );
                //.formLogin().loginProcessingUrl("/login/form");
                //.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login/form"));
                 //   form -> form
                 //       .loginPage("/login/form")
                 //       .loginProcessingUrl("/login")
                 //       .defaultSuccessUrl("/users")
                 //       .permitAll()
                //).logout(
                //    logout -> logout
                //        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //        .permitAll()
                //);

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


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().antMatchers("/auth");
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }


}
