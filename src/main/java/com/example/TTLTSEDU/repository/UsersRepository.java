package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // Tìm kiếm username có tồn tại trong DB không lúc đăng nhập
    Optional<Users> findByUsername(String username);

    // Kiểm tra username đã tồn tại trong DB chưa, khi tạo dữ liệu
    Boolean existsByUsername(String username);

    // Kiểm tra email đã tồn tại trong DB chưa, khi tạo dữ liệu
    Boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

}
