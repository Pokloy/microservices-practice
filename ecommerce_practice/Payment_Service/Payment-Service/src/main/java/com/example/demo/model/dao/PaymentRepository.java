package com.example.demo.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.dao.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
