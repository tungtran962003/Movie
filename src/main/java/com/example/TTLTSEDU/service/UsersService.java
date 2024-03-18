package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.request.UsersRequest;

import java.util.List;

public interface UsersService {

    List<Users> getAll();

    Users getOne(Integer id);

    Users getUserByEmail(String email);

    void add(UsersRequest usersRequest);

    Boolean update(UsersRequest usersRequest, Integer id);

    Boolean delete(Integer id);
}
