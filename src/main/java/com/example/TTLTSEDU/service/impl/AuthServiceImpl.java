package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.ConfirmEmail;
import com.example.TTLTSEDU.entity.UserStatus;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.ConfirmEmailRepository;
import com.example.TTLTSEDU.repository.UsersRepository;
import com.example.TTLTSEDU.service.AuthService;
import com.example.TTLTSEDU.util.HandleCode;
import com.example.TTLTSEDU.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private HandleCode handleCode;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ConfirmEmailRepository confirmEmailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean sendCodeEmail(String email, String title) {
        Users users = usersRepository.findByEmail(email).orElse(null);
        if (users != null) {
            String code = handleCode.generateCode();
            ConfirmEmail confirmEmail = new ConfirmEmail();
            confirmEmail.setUsers(users);
            confirmEmail.setConfirmCode(code);
            confirmEmail.setRequiredDateTime(new Date());
            Date expired = new Date(System.currentTimeMillis() + 120000);
            confirmEmail.setExpiredDateTime(expired);
            confirmEmail.setIsConfirm(false);
            confirmEmailRepository.save(confirmEmail);
            String body = mailUtil.confirmEmail(email, code);
            mailUtil.sendEmail(email, title, body);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer forgotPassword(String email, String newPassword, String reNewPassword) {
        Users users = usersRepository.findByEmail(email).orElse(null);
        if (users == null) {
            return 1;
        } else if (!newPassword.equals(reNewPassword)) {
            return 2;
        } else {
            users.setPassword(passwordEncoder.encode(newPassword));
            usersRepository.save(users);
            return 0;
        }
    }

    @Override
    public Integer resetPassword(String email, String currentPassword, String newPassword, String reNewPassword) {
        Users users = usersRepository.findByEmail(email).orElse(null);
        if (users == null) {
            return 1; // Tài khoản chưa được đăng kí
        } else if (!passwordEncoder.matches(currentPassword, users.getPassword())) {
            return 2; // mật khẩu hiện tại không khớp với mật khẩu đã đăng kí trước đó
        } else if (!newPassword.equals(reNewPassword)) {
            return 3; // 2 mật khẩu không khớp nhau
        } else {
            users.setPassword(passwordEncoder.encode(newPassword));
            usersRepository.save(users);
            return 0;
        }
    }

    @Override
    public Integer confirmCodeEmail(String email, String code) {
        Users users = usersRepository.findByEmail(email).orElse(null);
        if (users == null) {
            return 3; // Tài khoản chưa được đăng ký
        }
        ConfirmEmail confirmEmail = confirmEmailRepository.findTopByUsersIdOrderByIdDesc(users.getId());
        Integer status = handleCode.checkConfirmCode(confirmEmail, code);
        if (status == 0) {
            return 0; // mã hết hạn
        } else if (status == 2) {
            return 2; // error code
        }
        users.setIsActive(true);
        users.setUserStatus(UserStatus.builder().id(1).build());
        usersRepository.save(users);
        confirmEmail.setIsConfirm(true);
        confirmEmailRepository.save(confirmEmail);
        return 1; // ok
    }
}
