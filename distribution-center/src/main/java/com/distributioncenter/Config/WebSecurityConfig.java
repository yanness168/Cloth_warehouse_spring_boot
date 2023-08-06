package com.distributioncenter.Config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*@Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(encoder.encode("userpass")).roles("USER").build());
        manager.createUser(User.withUsername("employee").password(encoder.encode("employeepass")).roles("EMPLOYEE").build());
        manager.createUser(User.withUsername("admin").password(encoder.encode("adminpass")).roles("ADMIN").build());
        return manager;
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.authorizeHttpRequests()
                //.requestMatchers(toH2Console()).permitAll()
                /*.requestMatchers(AntPathRequestMatcher.antMatcher("/api/admin/**")).hasRole("ADMIN")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/user/**")).hasRole("USER")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/employee/**")).hasRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")
                //.defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()*/
                .headers()
                .frameOptions()
                .disable();

        http
                .csrf()
                .disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
}
