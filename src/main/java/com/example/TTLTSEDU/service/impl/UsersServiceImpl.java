package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.UsersRepository;
import com.example.TTLTSEDU.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users getOne(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }
}
