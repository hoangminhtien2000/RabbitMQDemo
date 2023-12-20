package com.example.rabbitmqtutorial.repository;

import com.example.rabbitmqtutorial.domain.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepo extends JpaRepository<Shipper,Integer> {
}
