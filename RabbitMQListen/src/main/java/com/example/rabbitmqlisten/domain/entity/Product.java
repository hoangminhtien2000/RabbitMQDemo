package com.example.rabbitmqlisten.domain.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@DynamicUpdate
@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "normalized_name")
    private String normalizedName;

    @Column(name = "quantity")
    private Integer quantity;
}
