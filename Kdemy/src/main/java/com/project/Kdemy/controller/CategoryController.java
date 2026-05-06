package com.project.Kdemy.controller;

import com.project.Kdemy.dto.CategoryRequestDto;
import com.project.Kdemy.model.Category;
import com.project.Kdemy.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@AllArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(
            @RequestBody CategoryRequestDto req) {

        String name = req.getName();
        return ResponseEntity.ok(categoryService.createCategory(name));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
