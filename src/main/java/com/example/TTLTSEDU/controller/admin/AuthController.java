package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.entity.Role;
import com.example.TTLTSEDU.entity.UserStatus;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.payload.request.SigninRequest;
import com.example.TTLTSEDU.payload.request.SignupRequest;
import com.example.TTLTSEDU.payload.response.JwtResponse;
import com.example.TTLTSEDU.payload.response.MessageResponse;
import com.example.TTLTSEDU.repository.RoleRepository;
import com.example.TTLTSEDU.repository.UsersRepository;
import com.example.TTLTSEDU.request.ConfirmEmailRequest;
import com.example.TTLTSEDU.request.SendCodeEmailRequest;
import com.example.TTLTSEDU.security.jwt.JwtUtils;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import com.example.TTLTSEDU.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;



    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest) {
        if (usersRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("The username existed! Please try again"));
        }
        if (usersRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email is already in use! Please try again"));
        }
        Users users = new Users(signupRequest.getName(), signupRequest.getUsername(), signupRequest.getEmail(), signupRequest.getPhoneNumber(), passwordEncoder.encode(signupRequest.getPassword()));
        String roleName = signupRequest.getRoleName();
        Role roleSignup = new Role();
        if (roleName == null) {
            roleSignup = roleRepository.findByRoleName("User").orElseThrow(() -> new RuntimeException("Role is not found"));
        } else {
            switch (roleName) {
                case "Admin":
                    roleSignup = roleRepository.findByRoleName("Admin").orElseThrow(() -> new RuntimeException("Role is not found"));
                    break;
                case "Staff":
                    roleSignup = roleRepository.findByRoleName("Staff").orElseThrow(() -> new RuntimeException("Role is not found"));
                    break;
                default:
                    roleSignup = roleRepository.findByRoleName("User").orElseThrow(() -> new RuntimeException("Role is not found"));
                    break;
            }
        }
        users.setRole(roleSignup);
        users.setIsActive(true);
        users.setPoint(0);
        users.setUserStatus(UserStatus.builder().id(2).build());
        usersRepository.save(users);
        return ResponseEntity.ok(new MessageResponse("Please confirm your account"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SigninRequest signinRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signinRequest.getUsername(), signinRequest.getPassword()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.ok(new MessageResponse("Username or password is incorrect"));
        }
        Users users = usersRepository.findByUsername(signinRequest.getUsername()).orElse(null);
        if (users.getUserStatus().getId() == 2) {
            return ResponseEntity.ok(new MessageResponse("Account has not been activated"));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.genarateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roleName = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toString();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                roleName, "Xin chào " + userDetails.getName()));
    }

    @PostMapping("/sendCodeEmail")
    public ResponseEntity<?> sendCodeEmail(@RequestBody @Valid SendCodeEmailRequest sendCodeEmailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (authService.sendCodeEmail(sendCodeEmailRequest.getEmail(), "Confirm Email")) {
                return ResponseEntity.ok("The confirmation code has been sent to your email");
            } else {
                return ResponseEntity.ok("Email is not registered");
            }
        }
    }

    @PostMapping("/sendCodeForgotPasswrod")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid SendCodeEmailRequest sendCodeEmailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (authService.sendCodeEmail(sendCodeEmailRequest.getEmail(), "Forgot password code")) {
                return ResponseEntity.ok("The confirmation code has been sent to your email");
            } else {
                return ResponseEntity.ok("Email is not registered");
            }
        }
    }

    @PostMapping("/confirmEmailForgotPassword")
    public ResponseEntity<?> confirmEmailCodeForgotPassword(@RequestBody @Valid ConfirmEmailRequest confirmEmailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            switch (authService.confirmCodeEmail(confirmEmailRequest.getEmail(), confirmEmailRequest.getCode())) {
                case 1:
                    return ResponseEntity.ok("Please enter a new password");
                case 2:
                    return ResponseEntity.ok("Incorrect code");
                case 3:
                    return ResponseEntity.ok("Account is not registered");
                case 0:
                    return ResponseEntity.ok("The authentication code has expired");
            }
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/confirmEmail")
    public ResponseEntity<?> confirmEmailCode(@RequestBody @Valid ConfirmEmailRequest confirmEmailRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            switch (authService.confirmCodeEmail(confirmEmailRequest.getEmail(), confirmEmailRequest.getCode())) {
                case 1:
                    return ResponseEntity.ok("Your account has been activated");
                case 2:
                    return ResponseEntity.ok("Incorrect code");
                case 3:
                    return ResponseEntity.ok("Account is not registered");
                case 0:
                    return ResponseEntity.ok("The authentication code has expired");
            }
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String newPassword,
                                            @RequestParam String reNewPassword,
                                            @RequestParam String email) {
        return switch (authService.forgotPassword(email, newPassword, reNewPassword)) {
            case 1 -> ResponseEntity.ok("Account is not registered");
            case 2 -> ResponseEntity.ok("The 2 passwords do not match");
            case 0 -> ResponseEntity.ok("OK");
            default -> ResponseEntity.ok(null);
        };
    }

    @PostMapping("/auth/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam String currentPassword,
                                           @RequestParam String newPassword,
                                           @RequestParam String rePassword) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return switch (authService.resetPassword(userDetails.getEmail(), currentPassword, newPassword, rePassword)) {
            case 1 -> ResponseEntity.ok("Tài khoản chưa được đăng ký");
            case 2 -> ResponseEntity.ok("Mật khẩu hiện tại chưa đúng");
            case 3 -> ResponseEntity.ok("Xác nhận mật khẩu và mật khẩu không khớp");
            case 0 -> ResponseEntity.ok("Đổi mật khẩu thành công");
            default -> ResponseEntity.ok(null);
        };
    }

    @GetMapping("/access-denied")
    public ResponseEntity<?> accessDeniedHandler() {
        return ResponseEntity.ok("Không có quyền truy cập");
    }
}
