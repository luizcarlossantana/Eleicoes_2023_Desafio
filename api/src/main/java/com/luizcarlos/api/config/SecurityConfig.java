package com.luizcarlos.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter ;

    @Autowired
    CorsConfig corsConfig;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.authorizeHttpRequests(
          authorizeConfig->{

              authorizeConfig.requestMatchers(HttpMethod.POST,"auth/logar").permitAll();
              authorizeConfig.requestMatchers(HttpMethod.GET,"eleitores/listar").permitAll();
              authorizeConfig.requestMatchers(HttpMethod.POST,"auth/register").hasRole("ADMIN");
              authorizeConfig.anyRequest().authenticated();
          })

                .csrf(crsf->crsf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
