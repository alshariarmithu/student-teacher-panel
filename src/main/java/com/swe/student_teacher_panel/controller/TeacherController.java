package com.swe.student_teacher_panel.controller;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swe.student_teacher_panel.entity.Role;
import com.swe.student_teacher_panel.entity.User;
import com.swe.student_teacher_panel.repository.RoleRepository;
import com.swe.student_teacher_panel.repository.UserRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherController(UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<User> students = userRepository.findByRoles_Name("ROLE_STUDENT");
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/add-student")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new User());
        return "add-student";
    }

    @PostMapping("/add-student")
    public String saveStudent(@ModelAttribute("student") User student) {
        Role studentRole = roleRepository.findByName("ROLE_STUDENT").orElseThrow();
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRoles(Set.of(studentRole));
        userRepository.save(student);
        return "redirect:/teacher/students";
    }


    @GetMapping("/edit-student/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        User student = userRepository.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/edit-student/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") User updatedStudent) {
        User student = userRepository.findById(id).orElseThrow();
        student.setUsername(updatedStudent.getUsername());
        if(!updatedStudent.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(updatedStudent.getPassword()));
        }
        userRepository.save(student);
        return "redirect:/teacher/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/teacher/students";
    }
}
