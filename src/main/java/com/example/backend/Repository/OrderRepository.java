package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
