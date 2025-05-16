package com.Amsall.amsallApp.repositories;

import com.Amsall.amsallApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoy extends JpaRepository<Product, Long>{
}
