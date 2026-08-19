package com.spring_bandend.spring_bandend.security;
import com.spring_bandend.spring_bandend.base.BaseError;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.UUID;
final class SecurityErrorWriter {
    private SecurityErrorWriter() {}
    static void write(ObjectMapper mapper, HttpServletResponse response, HttpStatus status, String detail, String instance) throws IOException {
        String trackingId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(BaseError.of(status.value(), status.getReasonPhrase(), detail)));
    }
}
