package com.project.Kdemy.service;

import com.project.Kdemy.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name);

    public List<Category> getAllCategories();
}
