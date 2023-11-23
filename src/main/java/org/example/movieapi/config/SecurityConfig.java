package org.example.movieapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity,
        HandlerMappingIntrospector introspector
    ) throws Exception
    {
        var mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        return httpSecurity
            // CORS: place CORS filter before others
            .cors(httpSecurityCorsConfigurer -> {})
            // CSRF disable (because REST API)
            .csrf(AbstractHttpConfigurer::disable)
            // authorizations
            .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> 
                authorizeHttpRequestsCustomizer
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(
                        mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/**")
                    ).hasRole("USER_ROLE")
                    .requestMatchers(
                        mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/**"),
                        mvcMatcherBuilder.pattern(HttpMethod.PUT, "/api/**"),
                        mvcMatcherBuilder.pattern(HttpMethod.PATCH, "/api/**"),
                        mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/api/**")

                    ).hasRole("ADMIN_ROLE")
                    .anyRequest().denyAll()
            )
            // authentication
            .httpBasic(httpSecurityHttpBasicConfigurer -> {})
            // sessions
            .sessionManagement(httpSecuritySessionManagerConfigurer ->
                httpSecuritySessionManagerConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )   
            // finalize security chain
            .build();
    }

    
}
