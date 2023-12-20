package com.example.rabbitmqlisten2.repository;

import com.example.rabbitmqlisten2.domain.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepo extends JpaRepository<Shipper,Integer> {
}
