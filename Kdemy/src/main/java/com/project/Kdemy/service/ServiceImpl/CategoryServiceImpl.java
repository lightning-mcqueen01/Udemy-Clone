package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.exception.AlreadyExistsException;
import com.project.Kdemy.model.Category;
import com.project.Kdemy.repository.CategoryRepository;
import com.project.Kdemy.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    public final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name) {

        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException("Category already exists");
        }

        Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
