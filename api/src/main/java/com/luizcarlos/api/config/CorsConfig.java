package com.luizcarlos.api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList(
                "https://front-eleicoes.vercel.app",
                "http://localhost:5173"
        ));
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        // Permitir credenciais (tokens)
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config); // Aplicando a todos os endpoints

        return new CorsFilter(source);
    }

}
