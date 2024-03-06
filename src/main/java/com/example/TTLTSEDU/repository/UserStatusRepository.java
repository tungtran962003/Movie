package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
}
