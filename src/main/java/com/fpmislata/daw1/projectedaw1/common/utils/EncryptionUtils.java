package com.fpmislata.daw1.projectedaw1.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionUtils {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean checkPassword(String password, String hash) {
        return PASSWORD_ENCODER.matches(password, hash);
    }
}
