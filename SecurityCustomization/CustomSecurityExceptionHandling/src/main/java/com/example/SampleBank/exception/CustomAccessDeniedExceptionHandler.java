package com.example.SampleBank.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("WWW-Authenticate", " UnAuthorized");
        LocalDateTime dateTime = LocalDateTime.now();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String msg = (accessDeniedException != null && accessDeniedException.getMessage()!=null)?"Exception thrown "+accessDeniedException.getMessage():" Unauthorized";
        response.setContentType("application/json;charset=UTF-8");
        String responseJson = String.format( "{ \"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\" }",
                dateTime,HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.getReasonPhrase(),msg, request.getRequestURI());
        response.getWriter().write(responseJson);
    }
}
