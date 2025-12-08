package com.example.SampleBank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Value("${user}")
    public String user;

    @Value("${pwd}")
    public String pwd;//sohan123

    @Value("${admin}")
    public String admin;

    @Value("${adminPwd}")
    public String adminPwd;//admin123

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       // http.authorizeHttpRequests(auth -> auth.anyRequest().denyAll());-->(sign-in)-->return 403
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/myAccount","/cards","/loans").authenticated().
                requestMatchers("/sayHello","/contactUs","/error").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
     return http.build();
    }
    @Bean
    public UserDetailsService userDetailsManager()
    {
        UserDetails user1 = User.withUsername(user).password(pwd).authorities("read").build();
        UserDetails user2 =User.withUsername(admin).password(adminPwd).authorities("admin").build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

}
