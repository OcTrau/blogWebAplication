package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //improtant
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELIST = {
            "/",
            "/index",
            "/login",
            "/register",
            "/db-console/**",
            "/css/**",
            "/img/**",
            "/js/**",
    };


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITELIST)
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/logout?success")
                )
                .httpBasic(Customizer.withDefaults())


        ;


        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions(frameOption->frameOption.disable()));



        return http.build();
    }


//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .authorizeHttpRequests((requests) -> requests
//                    .requestMatchers("/", "/index").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin((form) -> form
//                    .loginPage("/login")
//                    .permitAll()
//            );
//
//    return http.build();
//}



}
