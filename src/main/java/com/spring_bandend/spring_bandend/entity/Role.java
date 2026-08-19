package com.spring_bandend.spring_bandend.entity;
import com.spring_bandend.spring_bandend.entity.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    private String name;
    private String description;
}
