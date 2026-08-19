package com.spring_bandend.spring_bandend.entity;
import com.spring_bandend.spring_bandend.entity.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String username;

    @Column(length = 100)
    private String nickName;

    @Column(nullable = false)
    private String passwordHash;
    @Column(name = "enabled")
    private Boolean enabled;
}
