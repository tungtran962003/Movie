package com.example.TTLTSEDU.security.service;

import com.example.TTLTSEDU.entity.RankCustomer;
import com.example.TTLTSEDU.entity.UserStatus;
import com.example.TTLTSEDU.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Spring Security sử dụng 1 đối tượng UserDetails để chứa toàn bộ thông tin người dùng. Nên cần tạo class UserDetailsImpl
// giúp chuyển các thông tin của Entity User thành UserDetails
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private Integer id;

    private Integer point;
    private String username;

    private String email;

    private String name;

    private String phoneNumber;

    @JsonIgnore
    private String password;

    private RankCustomer rankCustomer;

    private UserStatus userStatus;

    private Boolean isActive;

    // Vì role liên quan đến hàm getAuthorities() của interface UserDetails nên phải để kiểu dữ liệu giống với hàm đó
    private Collection<? extends GrantedAuthority> roles;

    // Sau khi login cần build User trong package model trên hệ thống và cần vùng nhớ để lưu lại => static
    public static UserDetailsImpl build(Users users) {
        GrantedAuthority role = new SimpleGrantedAuthority(users.getRole().getRoleName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(role);
        return new UserDetailsImpl(
                users.getId(),
                users.getPoint(),
                users.getUsername(),
                users.getEmail(),
                users.getName(),
                users.getPhoneNumber(),
                users.getPassword(),
                users.getRankCustomer(),
                users.getUserStatus(),
                users.getIsActive(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { // Xem tài khoản người dùng có hết hạn hay không
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // Xem tài khoản người dùng có bị khóa không
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // Xem chứng chỉ (mật khẩu) của người dùng có hết hạn không
        return true;
    }

    @Override
    public boolean isEnabled() { // Xem tài khoản người dùng có được kích hoạt hay không
        return true;
    }
}
