package com.digio.esign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AttachmentConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
