package com.med.sid.billingservice.repository;

import com.med.sid.billingservice.entites.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepo extends JpaRepository<ProductItem, Long> {
}
