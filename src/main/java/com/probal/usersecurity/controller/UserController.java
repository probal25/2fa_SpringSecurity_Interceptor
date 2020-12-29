package com.probal.usersecurity.controller;

import com.probal.usersecurity.model.User;
import com.probal.usersecurity.service.OtpService;
import com.probal.usersecurity.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private OtpService otpService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object showUser(HttpServletRequest request){
        return "user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public Object showAdmin(HttpServletRequest request){
        return "admin";
    }

    @RequestMapping(value = "/otp", method = RequestMethod.GET)
    public String getOtp(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();

        session.setAttribute("otp_done", "FALSE");

        if(userName.equals("anonymousUser") || userName.equals(null)) {
            System.out.println(userName + " now in here");
            response.sendRedirect("/login");
        }else {
            if (session.getAttribute("otp_done").equals("TRUE")){
                response.sendRedirect("/");
            }

            String code = otpService.otpGenerator();
            String status = (String) session.getAttribute("otp_done");
            System.out.println(code);
            otpService.addOtp(userName, code);


            System.out.println(status);
            session.setAttribute("otp_code", code);
        }

        return "otp";

    }

    @RequestMapping(value = "/otp_validation", method = RequestMethod.POST)
    public Object postOtp(HttpServletRequest request, HttpSession session, @RequestParam String otp_value){

        String code = (String) session.getAttribute("otp_code");

        System.out.println("Set code : " +code);
        System.out.println("Your code : " + otp_value);


        if (otp_value.equals(code)) {
            session.setAttribute("otp_done", "TRUE");
            System.out.println(session.getAttribute("otp_done"));
            return "index";
        }
        else {
            session.setAttribute("otp_done", "FALSE");
            return "otp";
        }
    }
}
