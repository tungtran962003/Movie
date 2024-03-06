package com.example.TTLTSEDU.service;

public interface AuthService {

    Integer confirmCodeEmail(String email, String code);

    Boolean sendCodeEmail(String email, String title);

    Integer forgotPassword(String email, String newPassword, String reNewPassword);

    Integer resetPassword(String email, String currentPassword, String newPassword, String reNewPassword);
}
