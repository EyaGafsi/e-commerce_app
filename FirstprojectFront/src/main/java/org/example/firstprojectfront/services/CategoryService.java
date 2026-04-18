package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Category;
import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Category updateCategory(Long id, Category c) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category=categoryOptional.get();
            category.setName(c.getName());
            category.setDescription(c.getDescription());
            return categoryRepository.save(category);
        }
        else return null;
    }
    public long countCategories() {
        return categoryRepository.count();
    }
}
