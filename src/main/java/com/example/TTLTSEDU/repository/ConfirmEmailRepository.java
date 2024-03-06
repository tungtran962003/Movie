package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.ConfirmEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmEmailRepository extends JpaRepository<ConfirmEmail, Integer> {


    ConfirmEmail findByUsersId(Integer usersId);

    ConfirmEmail findTopByUsersIdOrderByIdDesc(Integer usersId);
}
