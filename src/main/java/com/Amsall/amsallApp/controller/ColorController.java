package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.models.Color;
import com.Amsall.amsallApp.services.ColorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("registerColor")
    public void addColor(@RequestBody Color color){
        colorService.saveColor(color);
    }
}
