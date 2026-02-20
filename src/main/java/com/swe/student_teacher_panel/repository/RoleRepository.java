package com.swe.student_teacher_panel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.student_teacher_panel.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
