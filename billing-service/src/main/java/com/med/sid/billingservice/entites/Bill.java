package com.med.sid.billingservice.entites;

import com.med.sid.billingservice.model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date bill_date;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productsItems;
    @Transient
    private Customer customer;
}
