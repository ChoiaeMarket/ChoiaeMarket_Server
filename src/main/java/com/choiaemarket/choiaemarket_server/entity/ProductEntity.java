package com.choiaemarket.choiaemarket_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="product")
@Table(name="product")
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int productNumber;
    private String idol;
    private String type;
    private String name;
    private String image;
    private int priceAvg;
    private int soldCount;
    private int favoriteCount;
    
}
