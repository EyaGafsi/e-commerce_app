package org.example.firstprojectfront.controllers;

import org.example.firstprojectfront.entities.Category;
import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.services.CategoryService;
import org.example.firstprojectfront.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public Category createCategory(
            @RequestBody Category category
    ) {

        return categoryService.createCategory(category);
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
    }
    @PutMapping
    public Category updateCategory(
            @RequestBody Category category
    ){
        return categoryService.createCategory(category);
    }
}
