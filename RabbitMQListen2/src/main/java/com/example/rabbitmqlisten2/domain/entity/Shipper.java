package com.example.rabbitmqlisten2.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@DynamicUpdate
@Entity
@Data
@Table(name = "shipper")
public class Shipper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "normalized_name")
    private String normalizedName;

    @Column(name = "phone")
    private String phone;

}