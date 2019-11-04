package com.geekshirt.orderservice.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Getter
@EnableJpaAuditing
@Configuration
@PropertySource({"classpath:application.properties"})
public class OrderServiceConfig {
    @Value("${customerservice.url}")
    private String customerServiceUrl;

    @Value("${paymentservice.url}")
    private String paymentServiceUrl;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
