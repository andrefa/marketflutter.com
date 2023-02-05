package com.market.flutter.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtRequestFilter jwtRequestFilter,
                                           JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) throws Exception {
        // We don't need CSRF for this example
        http.csrf().disable()
                // dont authenticate this particular request
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/login")
                .permitAll()
                // all other requests need to be authenticated
                .anyRequest()
                .authenticated()
                .and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Autowired
    public void configureGlobal(UserDetailsService jwtUserDetailsService,
            AuthenticationManagerBuilder auth,
            PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
