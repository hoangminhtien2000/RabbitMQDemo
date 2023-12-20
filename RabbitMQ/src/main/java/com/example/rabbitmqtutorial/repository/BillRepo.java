package com.example.rabbitmqtutorial.repository;

import com.example.rabbitmqtutorial.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill,Integer> {
}
