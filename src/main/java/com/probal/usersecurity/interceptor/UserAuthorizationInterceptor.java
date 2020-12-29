package com.probal.usersecurity.interceptor;

import com.probal.usersecurity.repository.UserRepository;
import com.probal.usersecurity.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserAuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpService otpService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession(false);

        String userName = loggedInUser.getName();
        String requestedUrl = request.getRequestURI();

        if(requestedUrl.equals("/") || requestedUrl.equals("/otp_validation")){
            System.out.println("Logged in as ---> " + userName );
            String message = "Logged in as ---> " + userName;
            request.setAttribute("message", message);
            return true;
        }
        else if (requestedUrl.equals("/otp")) {

            // If a user not logged in they can not access the OTP page

            if (userName.equals("anonymousUser")) {
                response.sendRedirect("/login");
                return false;
            }

            // if an authorized user with otp authenticated try to access the otp page then it will be access denied

            if(session.getAttribute("otp_done") != null) {
                if(session.getAttribute("otp_done").equals("TRUE")) {
                    response.getWriter().write("User already verified");
                    response.sendRedirect("/");
                    return false;
                }
                else { // still not finished the otp verification then they can access the otp page
                    return true;
                }
            }
            else {
                return true;
            }
        }
        else if (requestedUrl.equals("/admin")){

            // checking for unauthorized user

            if(userName.equals("anonymousUser")) {
                response.sendRedirect("/login");
                return false;
            }

            // Checking for OTP is already done or not

            if(session.getAttribute("otp_done") != null) {
                if(session.getAttribute("otp_done").equals("FALSE")) {
                    response.sendRedirect("/otp");
                    return false;
                }
            }

            // ACL

            if (loggedInUser.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
                return true;
            }
            else {
                response.getWriter().write("ACCESS DENIED");
                return false;
            }
        }
        else if (requestedUrl.equals("/user")){

            // checking for unauthorized user

            if(userName.equals("anonymousUser")) {
                response.sendRedirect("/login");
                return false;
            }

            // Checking for OTP is already done or not

            if(session.getAttribute("otp_done") != null) {
                if(session.getAttribute("otp_done").equals("FALSE")) {
                    response.sendRedirect("/otp");
                    return false;
                }
            }

            // ACL

            if (loggedInUser.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN") ||
                    loggedInUser.getAuthorities().stream().anyMatch(authority1 -> authority1.getAuthority().equals("USER")))) {
                return true;
            }
            else {
                response.getWriter().write("ACCESS DENIED");
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
