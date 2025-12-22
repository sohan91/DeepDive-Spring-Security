package com.example.SampleBank.config;

import com.example.SampleBank.exception.CustomAccessDeniedExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("!prod")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        return security
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/notices",
                                "/register",
                                "/contact",
                                "/error",
                                "/invalid",
                                "/exp"
                        ).permitAll()
                        .requestMatchers(
                                "/myAccount",
                                "/cards",
                                "/balance",
                                "/loans"
                        ).authenticated()
                )

                .formLogin(Customizer.withDefaults())
                .exceptionHandling(ehc ->
                        ehc.accessDeniedHandler(new CustomAccessDeniedExceptionHandler())
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .maximumSessions(1).maxSessionsPreventsLogin(true)
                )

                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
