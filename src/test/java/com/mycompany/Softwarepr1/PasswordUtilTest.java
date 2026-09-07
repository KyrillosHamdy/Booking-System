package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.util.*;

public class PasswordUtilTest {

    @Test
    public void testHashPassword() {
        String password = "mySecretPassword123";
        String hash = PasswordUtil.hashPassword(password);
        
        assertNotNull(hash, "Hash should not be null");
        assertNotEquals(password, hash, "Hash should not equal the plain text password");
        assertTrue(hash.startsWith("$2a$"), "BCrypt hash should start with $2a$");
    }

    @Test
    public void testCheckPasswordSuccess() {
        String password = "mySecretPassword123";
        String hash = PasswordUtil.hashPassword(password);
        
        assertTrue(PasswordUtil.verifyPassword(password, hash), "Password check should succeed for correct password");
    }

    @Test
    public void testCheckPasswordFailure() {
        String password = "mySecretPassword123";
        String wrongPassword = "wrongPassword123";
        String hash = PasswordUtil.hashPassword(password);
        
        assertFalse(PasswordUtil.verifyPassword(wrongPassword, hash), "Password check should fail for incorrect password");
    }
}
