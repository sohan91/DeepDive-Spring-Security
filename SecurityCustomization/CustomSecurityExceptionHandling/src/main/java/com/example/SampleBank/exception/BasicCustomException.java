package com.example.SampleBank.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;



public class BasicCustomException implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("WWW-Authenticate", " UnAuthorized");
        LocalDateTime dateTime = LocalDateTime.now();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String msg = (authException != null && authException.getMessage()!=null)?"Exception throwned "+authException.getMessage():" Unauthorized";
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = String.format( "{ \"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\" }",
                dateTime,HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase(),msg, request.getRequestURI());
        response.getWriter().write(responseJson);
    }
}
