package com.spring_bandend.spring_bandend.entity;

import com.spring_bandend.spring_bandend.entity.baseEntity.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Product extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
