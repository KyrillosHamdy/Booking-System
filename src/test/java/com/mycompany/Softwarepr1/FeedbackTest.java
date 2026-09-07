package com.mycompany.Softwarepr1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.Softwarepr1.models.*;

public class FeedbackTest {

    @Test
    public void testFeedbackSettersAndGetters() {
        Feedback feedback = new Feedback();
        User user = new User();
        
        feedback.setUser(user);
        feedback.setComment("Great experience!");

        assertEquals(user, feedback.getUser());
        assertEquals("Great experience!", feedback.getComment());
    }
}
