package com.example.demo.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.UserRepository;
import com.example.demo.model.dao.entity.UserEntity;
import com.example.demo.model.dto.PaymentSuccessEvent;
import com.example.demo.model.service.UserService;

@Service
public class UserServiceImpl extends UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public void finalizeOrder(PaymentSuccessEvent event) {
        System.out.println("Finalizing order: " + event.getOrderId());

        // Mock logic to finalize order
        if ("SUCCESS".equals(event.getStatus())) {
            System.out.println("Order " + event.getOrderId() + " is finalized with payment amount: $" + event.getAmount());
        } else {
            System.out.println("Order " + event.getOrderId() + " could not be finalized due to payment failure.");
        }
        
    }
}
