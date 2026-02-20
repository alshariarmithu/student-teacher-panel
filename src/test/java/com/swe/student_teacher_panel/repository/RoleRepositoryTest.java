package com.swe.student_teacher_panel.repository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.swe.student_teacher_panel.entity.Role;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Role studentRole;
    private Role teacherRole;

    @BeforeEach
    void setUp() {
        studentRole = new Role();
        studentRole.setName("ROLE_STUDENT_" + System.currentTimeMillis());
        roleRepository.save(studentRole);

        teacherRole = new Role();
        teacherRole.setName("ROLE_TEACHER_" + System.currentTimeMillis());
        roleRepository.save(teacherRole);
    }

    @Test
    void testFindByNameSuccess() {
        Optional<Role> foundRole = roleRepository.findByName(studentRole.getName());

        assertTrue(foundRole.isPresent());
        assertEquals(studentRole.getName(), foundRole.get().getName());
    }

    @Test
    void testFindByNameNotFound() {
        Optional<Role> foundRole = roleRepository.findByName("ROLE_ADMIN");

        assertFalse(foundRole.isPresent());
    }

    @Test
    void testSaveRole() {
        Role newRole = new Role();
        newRole.setName("ROLE_ADMIN_" + System.currentTimeMillis());

        Role savedRole = roleRepository.save(newRole);

        assertNotNull(savedRole.getId());
        assertEquals(newRole.getName(), savedRole.getName());
    }

    @Test
    void testMultipleRolesPersisted() {
        assertTrue(roleRepository.count() >= 2);
    }

}
