package com.example.TTLTSEDU.util;

import com.example.TTLTSEDU.entity.ConfirmEmail;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class HandleCode {

    public String generateCode() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.format("%06d", randomNumber);
    }

    public Integer checkConfirmCode(ConfirmEmail confirmEmail, String code) {
        Date presentTime = new Date();
        int result = confirmEmail.getExpiredDateTime().compareTo(presentTime);
        if (confirmEmail.getConfirmCode().equals(code)) {
            if (result < 0) {
                return 0; // Mã hết hạn
            } else {
                return 1; // ok
            }
        } else {
            return 2; // Sai mã
        }
    }

//    public String generatePassword() {
//
//    }

}
