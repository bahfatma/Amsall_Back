package com.Amsall.amsallApp.repositories;

import com.Amsall.amsallApp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
