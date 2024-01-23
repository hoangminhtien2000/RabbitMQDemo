package com.example.rabbitmqtutorial.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@DynamicUpdate
@Entity
@Data
@Table(name = "bill")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "shipper_id")
    private Integer shipperId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private String status;

    @Column(name = "address")
    private String address;
}
