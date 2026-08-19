package com.spring_bandend.spring_bandend.feature.auth.validator;

import com.spring_bandend.spring_bandend.entity.User;
import com.spring_bandend.spring_bandend.feature.core.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /** Username is stored as email; same pattern as {@code @Email} but enforced in code for login/register paths. */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
    private static final int PASSWORD_MIN = 8;
    private static final int PASSWORD_MAX = 100;
    /** At least one lower, upper, digit, and special character (after trim). */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$");
//    public User validateLoginCredentials(String username, String rawPassword) {
//        User user = userRepository.findByUsername(username).orElse(null);
//        if (user == null) {
//            throw new ValidationException("Invalid credentials");
//        }
//        if (!Boolean.TRUE.equals(user.getEnabled())) {
//            throw new ValidationException("Account is disabled");
//        }
//        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
//            throw new ValidationException("Invalid credentials");
//        }
//        return user;
//    }
public User validateLoginCredentials(String username, String rawPassword) {

    User user = userRepository.findByUsername(username).orElse(null);

    if (user == null) {
        System.out.println("USER NOT FOUND");
        throw new ValidationException("Invalid credentials");
    }

    System.out.println("Username = " + user.getUsername());
    System.out.println("Enabled = " + user.getEnabled());
    System.out.println("Hash = " + user.getPasswordHash());
    System.out.println("Raw password = " + rawPassword);

    boolean matched =
            passwordEncoder.matches(rawPassword, user.getPasswordHash());

    System.out.println("Password MATCH = " + matched);

    if (!Boolean.TRUE.equals(user.getEnabled())) {
        throw new ValidationException("Account is disabled");
    }

    if (!matched) {
        throw new ValidationException("Invalid credentials");
    }

    return user;
}
}
