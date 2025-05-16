package com.Amsall.amsallApp.services;

import com.Amsall.amsallApp.models.Product;
import com.Amsall.amsallApp.repositories.ProductRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {
    @Autowired
    private final ProductRepositoy articleRepository;

    public ArticlesService(ProductRepositoy articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Product> getAllArticles() {
        return articleRepository.findAll();
    }

    public Product addArticle(Product article) {
        return articleRepository.save(article);
    }
}
