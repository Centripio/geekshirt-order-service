package com.geekshirt.orderservice.client;

import com.geekshirt.orderservice.config.OrderServiceConfig;
import com.geekshirt.orderservice.dto.AccountDto;
import com.geekshirt.orderservice.dto.AddressDto;
import com.geekshirt.orderservice.dto.CreditCardDto;
import com.geekshirt.orderservice.dto.CustomerDto;
import com.geekshirt.orderservice.util.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
public class CustomerServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceConfig config;

    public CustomerServiceClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public AccountDto findAccount(String accountId) {
        AccountDto account = restTemplate.getForObject(config.getCustomerServiceUrl() + "/{id}", AccountDto.class, accountId);
        return account;
    }

    public AccountDto createDummyAccount() {
        AddressDto address =  AddressDto.builder().street("Mariano Otero")
                                .city("Guadalajara")
                                .state("Jalisco")
                                .country("Mexico").zipCode("12131")
                                .build();

        CustomerDto customer = CustomerDto.builder().lastName("Madero")
                                .firstName("Juan")
                                .email("juan.made@xyz.com")
                                .build();

        CreditCardDto creditCard = CreditCardDto.builder()
                                .nameOnCard("Juan Madero")
                                .number("4320 1231 4552 1234")
                                .expirationMonth("03")
                                .expirationYear("2023")
                                .type("VISA")
                                .ccv("123")
                                .build();

        AccountDto account = AccountDto.builder()
                                .address(address)
                                .customer(customer)
                                .creditCard(creditCard)
                                .status(AccountStatus.ACTIVE)
                                .build();

       return  account;
    }

    public AccountDto createAccount(AccountDto account) {
        return restTemplate.postForObject(config.getCustomerServiceUrl(), account, AccountDto.class);
    }

    public AccountDto createAccountBody(AccountDto account) {
        ResponseEntity<AccountDto> responseAccount = restTemplate.postForEntity(config.getCustomerServiceUrl(),
                                                                                account, AccountDto.class);
        log.info("Response: " +  responseAccount.getHeaders());
        return responseAccount.getBody();
    }

    public void updateAccount(AccountDto account) {
        restTemplate.put(config.getCustomerServiceUrl() + "/{id}", account, account.getId());
    }

    public void deleteAccount(AccountDto account) {
        restTemplate.delete(config.getCustomerServiceUrl() + "/{id}", account.getId());
    }
}
