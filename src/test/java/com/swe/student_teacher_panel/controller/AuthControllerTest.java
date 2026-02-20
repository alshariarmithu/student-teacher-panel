package com.swe.student_teacher_panel.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Test
    void testAuthControllerExists() {
        AuthController controller = new AuthController();
        assertNotNull(controller);
    }

    @Test
    void testLoginMethod() {
        AuthController controller = new AuthController();
        String result = controller.login();
        assertEquals("login", result);
    }

    @Test
    void testDashboardMethod() {
        AuthController controller = new AuthController();
        String result = controller.dashboard();
        assertEquals("dashboard", result);
    }

}
