package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane@example.com");
        user.setPhone("0987654321");
        user.setPassword("newPass");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("0987654321", user.getPhone());
        assertEquals("newPass", user.getPassword());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setEmail("jane@example.com");
        user.setPhone("0987654321");
        user.setPassword("newPass");

        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("0987654321", user.getPhone());
        assertEquals("newPass", user.getPassword());
    }
}
