package com.jordi.configuration;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jordi.components.JwtRequestFilter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
	@Autowired
	private JwtRequestFilter jwtFilter;
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
 
						.requestMatchers("/swagger-ui.html", 
								"/swagger-ui/**", 
								"/v3/api-docs/**",
								"/swagger-resources/**", 
								"/webjars/**", 
								"/api-docs/**", 
								"/swagger-ui/index.html")
						.permitAll()
 
						.requestMatchers(HttpMethod.POST, "/api/v1/users/user/validate").permitAll()
						.requestMatchers(HttpMethod.PUT, "/api/v1/users/user/block/").hasAnyRole("SYSTEM")
						.requestMatchers(HttpMethod.POST, "/api/v1/users/").hasAnyRole("ADMIN")
						//.requestMatchers(HttpMethod.GET, "/api/v1/users/").hasAnyRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("ADMIN")
						.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyRole("ADMIN")
 
						.requestMatchers(HttpMethod.GET, "/api/v1/vehicles/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.POST, "/api/v1/vehicles/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.PUT, "/api/v1/vehicles/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.DELETE, "/api/v1/vehicles/**").hasAnyRole("ADMIN")
 
						.requestMatchers(HttpMethod.GET, "/api/v1/brands/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.POST, "/api/v1/brands/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.PUT, "/api/v1/brands/**").hasAnyRole("ADMIN","USER")
						.requestMatchers(HttpMethod.DELETE, "/api/v1/brands/**").hasAnyRole("ADMIN")
 
 
                	.anyRequest().authenticated()
 
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
 
 
}