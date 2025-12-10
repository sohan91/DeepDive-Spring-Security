package com.example.SampleBank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(request-> request.anyRequest().authenticated());
        security.httpBasic(withDefaults());
        security.formLogin(withDefaults());

        return security.build();
    }

    @Bean
    public CompromisedPasswordChecker passwordChecker()
    {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }


}
