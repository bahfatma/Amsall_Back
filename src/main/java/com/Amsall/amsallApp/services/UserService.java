package com.Amsall.amsallApp.services;

import com.Amsall.amsallApp.exceptions.EmailAlreadyExistsException;
import com.Amsall.amsallApp.models.Role;
import com.Amsall.amsallApp.models.User;
import com.Amsall.amsallApp.repositories.RoleRepository;
import com.Amsall.amsallApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RoleRepository roleRepository;


    @Autowired
    public UserService(UserRepository usersRepository, EmailService emailService, RoleRepository roleRepository) {
        this.userRepository = usersRepository;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }

    private void validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailPattern)) {
            throw new IllegalArgumentException("L'adresse e-mail n'est pas valide.");
        }
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

    String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hachage du mot de passe.", e);
        }
    }

    public void saveUser(User user) {
        validateEmail(user.getEmail());

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("L'adresse e-mail saisie existe déjà. Veuillez vous connecter.");
        }

        validatePassword(user.getPassWord());
        user.setPassWord(hashPassword(user.getPassWord()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER introuvable"));

        user.getRoles().add(userRole);

        userRepository.save(user);
    }

    public ResponseEntity<?> loginUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(404).body("Adresse e-mail non trouvée.");
        }

        String hashedPassword = hashPassword(user.getPassWord());

        if (!existingUser.get().getPassWord().equals(hashedPassword)) {
            return ResponseEntity.status(401).body("Mot de passe incorrect.");
        }

        // Exemple de génération de token
        Map<String, String> response = Map.of("token", "dummyToken123"); // Remplacez apres par un vrai token
        return ResponseEntity.ok(response);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> getUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void processForgotPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Génère un token unique
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            userRepository.save(user);
            // Envoie l'email de réinitialisation
            emailService.sendResetPasswordEmail(email, resetToken);
        }
    }


    public ResponseEntity<String> resetPassword(String token, String newPassword) {
        Optional<User> optionalUser = userRepository.findByResetToken(token);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(400).body("Token invalide ou expiré.");
        }

        User user = optionalUser.get();
        validatePassword(newPassword); // Vérifie si le mot de passe respecte les critères
        user.setPassWord(hashPassword(newPassword));
        user.setResetToken(null); // Invalide le token après réinitialisation
        userRepository.save(user);

        return ResponseEntity.ok("Mot de passe réinitialisé avec succès.");

    }


}

