package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.UserStatus;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.UsersRepository;
import com.example.TTLTSEDU.request.UsersRequest;
import com.example.TTLTSEDU.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public void add(UsersRequest usersRequest) {
        Users users = usersRequest.add(new Users());
        users.setPassword(passwordEncoder.encode(usersRequest.getPassword()));
        usersRepository.save(users);
    }

    @Override
    public Boolean update(UsersRequest usersRequest, Integer id) {
        Users users = usersRepository.findById(id).orElse(null);
        if (users != null) {
            Users usersOld = users;
            Users usersUpdate = usersRequest.update(usersRequest, id);
            usersUpdate.setPoint(usersOld.getPoint());
            usersUpdate.setPassword(passwordEncoder.encode(usersRequest.getPassword()));
            usersUpdate.setUserStatus(usersOld.getUserStatus());
            usersRepository.save(usersUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Users users = usersRepository.findById(id).orElse(null);
        if (users != null) {
            users.setIsActive(false);
            usersRepository.save(users);
            return true;
        } else {
            return false;
        }
    }

}
