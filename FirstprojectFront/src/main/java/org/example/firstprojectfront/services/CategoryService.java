package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Category;
import org.example.firstprojectfront.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);

    }
    public Category getCategoryById(Long id) {
      return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
