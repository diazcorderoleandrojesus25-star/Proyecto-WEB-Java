package com.Jobxpress.Jobxpress.Config;

import com.Jobxpress.Jobxpress.Service.UsuarioDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final UsuarioDetailsServiceImpl usuarioDetailsService;

    public SecurityConfig(UsuarioDetailsServiceImpl usuarioDetailsService) {
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            // 🔥 Anti-caché para evitar volver después del logout
            .headers(headers -> headers
                .cacheControl(cache -> cache.disable())
            )

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/registro", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/cliente/**").hasAuthority("ROLE_CLIENTE")
                .requestMatchers("/prestador/**").hasAuthority("ROLE_PRESTADOR")
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(roleRedirectHandler())
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)      // 🔥 Invalida la sesión
                .deleteCookies("JSESSIONID")      // 🔥 Elimina cookies
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    // Redirección por rol
    @Bean
    public AuthenticationSuccessHandler roleRedirectHandler() {
        return (request, response, authentication) -> {

            var roles = authentication.getAuthorities();

            if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin/dashboard");
                return;
            }

            if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_CLIENTE"))) {
                response.sendRedirect("/cliente/home");
                return;
            }

            if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_PRESTADOR"))) {
                response.sendRedirect("/prestador/home");
                return;
            }

            response.sendRedirect("/");
        };
    }
}