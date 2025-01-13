package com.Amsall.amsallApp.service;

import com.Amsall.amsallApp.exceptions.EmailAlreadyExistsException;
import com.Amsall.amsallApp.model.Users;
import com.Amsall.amsallApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    public void saveUser(Users user) {
        // Vérifie si l'adresse e-mail existe déjà
        Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("L'adresse e-mail saisie existe déjà. Veuillez vous connecter.");
        }
        // sinon Sauvegarde l'utilisateur
        userRepository.save(user);
    }
}

