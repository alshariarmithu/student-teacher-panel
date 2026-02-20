package com.swe.student_teacher_panel.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swe.student_teacher_panel.entity.Role;
import com.swe.student_teacher_panel.entity.User;
import com.swe.student_teacher_panel.repository.RoleRepository;
import com.swe.student_teacher_panel.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            Role teacherRole = roleRepository.findByName("ROLE_TEACHER")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ROLE_TEACHER");
                        return roleRepository.save(r);
                    });

            Role studentRole = roleRepository.findByName("ROLE_STUDENT")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ROLE_STUDENT");
                        return roleRepository.save(r);
                    });

            if (userRepository.findByUsername("teacher").isEmpty()) {
                User teacher = new User();
                teacher.setUsername("teacher");
                teacher.setPassword(passwordEncoder.encode("teacher123"));
                teacher.setRoles(Set.of(teacherRole));

                userRepository.save(teacher);
                System.out.println("Default teacher created");
            }

            // ---------- CREATE STUDENT ----------
            if (userRepository.findByUsername("student").isEmpty()) {
                User student = new User();
                student.setUsername("student");
                student.setPassword(passwordEncoder.encode("student123"));
                student.setRoles(Set.of(studentRole));

                userRepository.save(student);
                System.out.println("Default student created");
            }
        };
    }
}
