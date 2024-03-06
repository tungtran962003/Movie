package com.example.TTLTSEDU.security.jwt;

import com.example.TTLTSEDU.entity.RefreshToken;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.RefreshTokenRepository;
import com.example.TTLTSEDU.repository.UsersRepository;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret; // Secret key

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpiration; // Thời gian sống trên hệ thống - tính bằng mili giây

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    // Khi đăng nhập sẽ gọi đến hàm này
    public String genarateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // Lấy users hiện tại trên hệ thống
        // Build dữ liệu từ thông tin đăng nhập xuống ra 1 mã token
        RefreshToken refreshToken = new RefreshToken();
        String token = Jwts.builder()
                .setSubject((userDetails.getUsername())) // Set username
                .setIssuedAt(new Date()) // Set tại thời điểm nào
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration)) // Set thời gian sống
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        refreshToken.setToken(token);
        refreshToken.setUsers(Users.builder().id(userDetails.getId()).build());
        refreshToken.setExpiredTime(new Date((new Date()).getTime() + jwtExpiration));
        refreshTokenRepository.save(refreshToken);
        return token;
    }

    private Key getKey() {
        // Tạo 1 khóa HMA-SHA từ 1 đoạn mã token đã được giải mã dưới định dạng BASE64
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Kiểm tra token có hợp lệ hay không
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage()); // Token không đúng định dạng
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage()); // Token bị hết thời gian
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage()); // Không hỗ trợ token
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage()); // Có ký tự trống
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody().getSubject();
    }
}
