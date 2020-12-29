package com.probal.usersecurity.repository;

import com.probal.usersecurity.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    public Otp findOtpByUsername(String username);
}
