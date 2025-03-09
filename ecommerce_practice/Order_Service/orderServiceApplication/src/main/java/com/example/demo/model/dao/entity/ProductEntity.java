package com.example.demo.model.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pk")
    private Long idPk;
    
    @Column(name="product_name")
    private String name;
    
    @Column(name="product_price")
    private double price;
}
