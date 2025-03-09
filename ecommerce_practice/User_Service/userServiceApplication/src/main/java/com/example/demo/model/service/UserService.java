package com.example.demo.model.service;

import com.example.demo.model.dao.entity.UserEntity;
import java.util.List;

public abstract class UserService {
    public abstract List<UserEntity> getAllUsers();
    public abstract UserEntity getUserById(Long id);
    public abstract UserEntity createUser(UserEntity user);
    public abstract void deleteUser(Long id);
}
