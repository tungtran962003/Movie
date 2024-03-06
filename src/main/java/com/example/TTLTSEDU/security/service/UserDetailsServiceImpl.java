package com.example.TTLTSEDU.security.service;

import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    // Hàm tìm xem username có tồn tại trong DB không
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username " +username));
        return UserDetailsImpl.build(users);
    }
}
