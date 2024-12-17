package com.med.sid.billingservice;

import com.med.sid.billingservice.entites.Bill;
import com.med.sid.billingservice.entites.ProductItem;
import com.med.sid.billingservice.model.Customer;
import com.med.sid.billingservice.model.Product;
import com.med.sid.billingservice.repository.BillRepo;
import com.med.sid.billingservice.repository.ProductItemRepo;
import com.med.sid.billingservice.services.CustomerRestClient;
import com.med.sid.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepo billRepository,
                            ProductItemRepo productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient) {
        return args -> {
            Collection<Product> products = productRestClient.allProducts().getContent();
            Long customerId = 1L;
            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer == null) throw new RuntimeException("Customer not found");
            Bill bill = new Bill();
            bill.setBill_date(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill = billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setBill(savedBill);
                productItem.setProductId(product.getId());
                productItem.setQuantity(1 + new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });
        };
    }

}
