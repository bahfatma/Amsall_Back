package com.Amsall.amsallApp.services;

import com.Amsall.amsallApp.models.Color;
import com.Amsall.amsallApp.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public void saveColor(Color color){
        colorRepository.save(color);
    }
}
