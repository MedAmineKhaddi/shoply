package com.med.sid.customerservice;

import com.med.sid.customerservice.config.CustomerConfigParams;
import com.med.sid.customerservice.entites.Customer;
import com.med.sid.customerservice.repositories.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CustomerRepo customerRepo, RepositoryRestConfiguration restConfig) {
        restConfig.exposeIdsFor(Customer.class);
        return args -> {
            customerRepo.saveAll(List.of(
                    Customer.builder().name("Mohamed").email("med@mail.com").build(),
                    Customer.builder().name("Hamza").email("hamza@mail.com").build(),
                    Customer.builder().name("Ayoub").email("ayoub@mail.com").build()
            ));
            customerRepo.findAll().forEach(System.out::println);
        };
    }
}
