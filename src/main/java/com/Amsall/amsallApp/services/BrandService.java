package com.Amsall.amsallApp.services;

import com.Amsall.amsallApp.models.Brand;
import com.Amsall.amsallApp.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void saveBrand(Brand brand){
        brandRepository.save(brand);
    }
}
