package com.spring_bandend.spring_bandend.security;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;
@Component // wired in SecurityConfig.exceptionHandling
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private final ObjectMapper objectMapper; // serialize BaseError to JSON
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        SecurityErrorWriter.write(objectMapper, response, HttpStatus.UNAUTHORIZED, "Authentication required. Please sign in again.", request.getRequestURI());
    }
}
