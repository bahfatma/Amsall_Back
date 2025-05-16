package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.models.Product;
import com.Amsall.amsallApp.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticlesController {
    @Autowired
    private ArticlesService articleService;

    @GetMapping
    public List<Product> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("/save_article")
    public Product addArticle(@RequestBody Product article) {
        return articleService.addArticle(article);
    }
}
