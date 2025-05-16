package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.models.Category;
import com.Amsall.amsallApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/save_category")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}
