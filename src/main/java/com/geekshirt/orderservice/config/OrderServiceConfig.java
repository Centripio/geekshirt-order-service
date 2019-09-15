package com.geekshirt.orderservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderServiceConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
