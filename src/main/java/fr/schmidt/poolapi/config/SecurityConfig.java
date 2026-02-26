package fr.schmidt.poolapi.config;

import fr.schmidt.poolapi.security.JwtAuthFilter;
import fr.schmidt.poolapi.service.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // desactive CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // routes publiques
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Admin seulement
                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/subscriptions/kinds").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tickets/kinds").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pool").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pool/status").hasRole("ADMIN")

                        // Admin + Employee
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/access").hasAnyRole("ADMIN", "EMPLOYEE")

                        // Tous les authentifiés (càd avec token)
                        .requestMatchers(HttpMethod.GET, "/subscriptions/kinds").authenticated()
                        .requestMatchers(HttpMethod.GET, "/tickets/kinds").authenticated()
                        .requestMatchers(HttpMethod.POST, "/access/entry/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/access/exit/**").authenticated()
                        .anyRequest().authenticated()
                )
                // pas de session HTTP - chaque requête est indépendante
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(personDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
