package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("PUBLIC")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()

                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/", "/index.html", "/swagger-ui.html", "/swagger-ui/**")
                        .permitAll()
                )
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/v1/flight/v1/status/**")
                        .hasAnyRole("PUBLIC", "ADMIN")
                )
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/v1/flight/v1/airport", "/v1/flight/v1/flight",
                                "/v1/flight/v1/flightSchedule/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated())

                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
