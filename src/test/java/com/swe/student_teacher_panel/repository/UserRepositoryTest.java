package com.swe.student_teacher_panel.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swe.student_teacher_panel.entity.Role;
import com.swe.student_teacher_panel.entity.User;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User testUser;
    private Role studentRole;

    @BeforeEach
    void setUp() {
        studentRole = new Role();
        studentRole.setName("ROLE_STUDENT_" + System.currentTimeMillis());
        roleRepository.save(studentRole);

        testUser = new User();
        testUser.setUsername("testuser_" + System.currentTimeMillis());
        testUser.setPassword("password123");
        Set<Role> roles = new HashSet<>();
        roles.add(studentRole);
        testUser.setRoles(roles);
        userRepository.save(testUser);
    }

    @Test
    void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername(testUser.getUsername());

        assertTrue(foundUser.isPresent());
        assertEquals(testUser.getUsername(), foundUser.get().getUsername());
        assertEquals("password123", foundUser.get().getPassword());
    }

    @Test
    void testFindByUsernameNotFound() {
        Optional<User> foundUser = userRepository.findByUsername("nonexistent");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindByRoles_Name() {
        List<User> students = userRepository.findByRoles_Name(studentRole.getName());

        assertNotNull(students);
        assertFalse(students.isEmpty());
        assertTrue(students.stream().anyMatch(u -> u.getUsername().equals(testUser.getUsername())));
    }

    @Test
    void testFindByRoles_NameNotFound() {
        List<User> teachers = userRepository.findByRoles_Name("NON_EXISTENT_ROLE_" + System.currentTimeMillis());

        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());
    }

    @Test
    void testSaveUser() {
        User newUser = new User();
        newUser.setUsername("newuser_" + System.currentTimeMillis());
        newUser.setPassword("encodedPassword");
        newUser.setRoles(new HashSet<>()); 

        User savedUser = userRepository.save(newUser);

        assertNotNull(savedUser.getId());
        assertEquals(newUser.getUsername(), savedUser.getUsername());
    }

}
