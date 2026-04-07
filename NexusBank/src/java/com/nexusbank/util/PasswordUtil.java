package com.nexusbank.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash the password before saving to database
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Check if entered password matches the saved hash
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}