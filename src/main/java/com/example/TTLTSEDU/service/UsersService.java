package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> getAll();

    Users getOne(Integer id);

    Users getUserByEmail(String email);
}
