package com.example.SampleBank.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       // http.authorizeHttpRequests(auth -> auth.anyRequest().denyAll());-->(sign-in)-->return 403
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/myAccount","/cards","/loans").authenticated().
                requestMatchers("/sayHello","/contactUs").permitAll());
        http.formLogin(form->form.disable());
        http.httpBasic(withDefaults());
     return http.build();
    }
}
