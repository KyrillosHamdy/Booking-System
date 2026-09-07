package com.mycompany.Softwarepr1.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for secure password hashing and verification using BCrypt.
 */
public class PasswordUtil {

    private PasswordUtil() {}

    /**
     * Hashes a plain-text password using BCrypt.
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Verifies a plain-text password against a stored password.
     * Supports both BCrypt hashes (new accounts) and legacy plain-text passwords.
     */
    public static boolean verifyPassword(String plainTextPassword, String storedPassword) {
        try {
            return BCrypt.checkpw(plainTextPassword, storedPassword);
        } catch (IllegalArgumentException e) {
            // Fallback: legacy plain-text comparison for old accounts
            return plainTextPassword.equals(storedPassword);
        }
    }
}
