package com.swe.student_teacher_panel.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Test
    void testStudentControllerExists() {
        StudentController controller = new StudentController();
        assertNotNull(controller);
    }

    @Test
    void testStudentProfileMethod() {
        StudentController controller = new StudentController();
        String result = controller.profile();
        assertEquals("student-profile", result);
    }

}
