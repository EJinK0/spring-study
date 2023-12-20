package org.example;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class Main {
    @Value("${web-url}")
    private String WEB_URL;

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println(WEB_URL);
                registry.addMapping("/**")
                        .allowedOrigins(WEB_URL);
            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                WebMvcConfigurer.super.configureMessageConverters(converters);
                converters.add(new FormHttpMessageConverter());
            }
        };
    }
}