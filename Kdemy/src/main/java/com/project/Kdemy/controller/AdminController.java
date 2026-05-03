package com.project.Kdemy.controller;

import com.project.Kdemy.dto.UserResponseDto;
import com.project.Kdemy.model.Course;
import com.project.Kdemy.service.AdminService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@Data
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(adminService.getAllCourses());
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {

        adminService.deleteCourse(courseId);
        return ResponseEntity.ok("Course Deleted SuccessFuly");
    }

    @GetMapping("/users/role")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@RequestParam String role) {

        return ResponseEntity.ok(adminService.getUsersByRole(role));
    }

}
