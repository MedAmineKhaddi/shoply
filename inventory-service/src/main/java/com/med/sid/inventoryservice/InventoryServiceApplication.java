package com.med.sid.inventoryservice;

import com.med.sid.inventoryservice.entities.Product;
import com.med.sid.inventoryservice.repositories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepo repo, RepositoryRestConfiguration restConfig) {
        return args -> {
            restConfig.exposeIdsFor(Product.class);
            repo.saveAll(List.of(
                    Product.builder().name("Computer").price(5700.0).quantity(20).build(),
                    Product.builder().name("Jus").price(10.0).quantity(50).build(),
                    Product.builder().name("Cookies").price(2.0).quantity(17).build()
            ));
            repo.findAll().forEach(System.out::println);
        };
    }
}
