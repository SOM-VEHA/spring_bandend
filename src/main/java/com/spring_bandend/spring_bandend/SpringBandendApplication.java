package com.spring_bandend.spring_bandend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBandendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBandendApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = encoder.encode("12345678");

        System.out.println("Password : " + hash);
    }

}
