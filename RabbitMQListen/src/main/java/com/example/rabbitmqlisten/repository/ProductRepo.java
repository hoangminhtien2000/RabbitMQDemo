package com.example.rabbitmqlisten.repository;

import com.example.rabbitmqlisten.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
