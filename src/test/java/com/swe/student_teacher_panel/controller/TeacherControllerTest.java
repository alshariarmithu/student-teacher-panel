package com.swe.student_teacher_panel.controller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swe.student_teacher_panel.entity.User;
import com.swe.student_teacher_panel.repository.RoleRepository;
import com.swe.student_teacher_panel.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setUp() {
        assertNotNull(teacherController);
    }

    @Test
    void testFindStudentsByRole() {
        List<User> mockStudents = new ArrayList<>();
        User student1 = new User();
        student1.setId(1L);
        student1.setUsername("student1");
        mockStudents.add(student1);

        when(userRepository.findByRoles_Name("ROLE_STUDENT")).thenReturn(mockStudents);

        List<User> result = userRepository.findByRoles_Name("ROLE_STUDENT");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("student1", result.get(0).getUsername());
        verify(userRepository, times(1)).findByRoles_Name("ROLE_STUDENT");
    }

    @Test
    void testFindStudentsByRoleEmpty() {
        when(userRepository.findByRoles_Name("ROLE_STUDENT")).thenReturn(new ArrayList<>());

        List<User> result = userRepository.findByRoles_Name("ROLE_STUDENT");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveStudent() {
        User student = new User();
        student.setUsername("newstudent");
        student.setPassword("password123");

        when(userRepository.save(student)).thenReturn(student);
        User savedStudent = userRepository.save(student);

        assertNotNull(savedStudent);
        assertEquals("newstudent", savedStudent.getUsername());
        verify(userRepository, times(1)).save(student);
    }

}
