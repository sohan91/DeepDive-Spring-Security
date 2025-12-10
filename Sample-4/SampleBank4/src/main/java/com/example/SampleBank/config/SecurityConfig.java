package com.example.SampleBank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.
        csrf(csrf->csrf.disable())
                .authorizeHttpRequests(request-> request.requestMatchers("/myAccount","/cards","/balance","/loans").authenticated()
                        .requestMatchers("/notice","/error","/register","/contact").permitAll());
        security.httpBasic(withDefaults());
        security.formLogin(withDefaults());

        return security.build();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker()
    {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
