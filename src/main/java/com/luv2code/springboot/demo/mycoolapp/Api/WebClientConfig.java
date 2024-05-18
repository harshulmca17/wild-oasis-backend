package com.luv2code.springboot.demo.mycoolapp.Api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfig {
    @Bean
    @Primary
    @Qualifier("webClient")
    public RestTemplate webClient(){
        return new RestTemplate();
    }
}
