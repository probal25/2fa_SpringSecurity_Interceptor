package com.probal.usersecurity.service;

import com.probal.usersecurity.model.Otp;
import com.probal.usersecurity.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    public String findOtp(String username) {
        Otp otp = otpRepository.findOtpByUsername(username);
        String code = otp.getKey();
        return code;
    }

    public String otpGenerator () {
        return ((new Random().nextInt(999) * 1000) + "");
    }

    public void addOtp(String username, String code) {
        Otp otp = new Otp();
        otp.setUsername(username);
        otp.setKey(code);

        otpRepository.save(otp);
    }
}
