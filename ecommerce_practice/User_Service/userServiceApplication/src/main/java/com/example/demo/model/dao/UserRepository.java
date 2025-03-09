package com.example.demo.model.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.dao.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
