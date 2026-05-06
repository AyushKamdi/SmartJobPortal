package com.SmartJobPortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/*.html", "/assets/**", "/register").permitAll()
//                .anyRequest().authenticated()
//            )
//            // THIS IS THE NEW LOGIN CONFIGURATION!
//            .formLogin(form -> form
//                .loginPage("/login.html")                  // The page we want to show
//                .loginProcessingUrl("/perform_login")      // The hidden URL our HTML form will submit to
//                .defaultSuccessUrl("/dashboard.html", true)// Where to go if password is correct
//                .failureUrl("/login.html?error=true")      // Where to go if password is wrong
//                .permitAll()
//            )
//            .csrf(csrf -> csrf.disable()); 
//        
//        return http.build();
//    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF (Standard for simple college projects so your forms don't crash)
            .csrf(csrf -> csrf.disable()) 
            
            // 2. THE LOCK: Tell Spring which pages are public and which are private
            .authorizeHttpRequests(auth -> auth
                // These pages are PUBLIC (Anyone can see them)
                .requestMatchers("/login.html", "/register.html", "/register", "/assets/**").permitAll()
                
                // EVERYTHING ELSE is PRIVATE (Must be logged in to see them)
                .anyRequest().authenticated()
            )
            
            // 3. Keep your existing Login and Logout settings!
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/dashboard.html", true)
                .failureUrl("/login.html?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout=true")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}