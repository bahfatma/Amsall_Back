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

    private void validatePassword(String password) {
        // Vérifie la longueur minimale
        if (password.length() < 8) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères.");
        }

        // Vérifie la robustesse
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordPattern)) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        }
    }

    public void saveUser(Users user) {
        // Vérifie si l'adresse e-mail existe déjà
        Optional<Users> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("L'adresse e-mail saisie existe déjà. Veuillez vous connecter.");
        }

        // Valide le mot de passe avant de sauvegarder
        validatePassword(user.getPassWord());

        // Sauvegarde l'utilisateur
        userRepository.save(user);
    }
}

