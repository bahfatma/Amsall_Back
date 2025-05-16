package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.models.Brand;
import com.Amsall.amsallApp.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/registerBrand")
    public void addBrand(@RequestBody Brand brand){
        brandService.saveBrand(brand);
    }
}
