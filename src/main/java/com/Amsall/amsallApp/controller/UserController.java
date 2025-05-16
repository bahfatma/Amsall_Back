package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.exceptions.EmailAlreadyExistsException;
import com.Amsall.amsallApp.models.User;
import com.Amsall.amsallApp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @PostMapping("/register")
    public void addUser(@RequestBody User user ){
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<Boolean> verifyEmail(@RequestParam String email) {
        boolean exists = userService.emailExists(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/reset-password/request")
    public ResponseEntity<Map<String, String>> requestPasswordReset(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        userService.processForgotPassword(email);
        // Toujours renvoyer une réponse positive pour éviter de révéler l'existence d'un compte.
        return ResponseEntity.ok(Map.of("message", "Si l'adresse existe dans notre système, un email de réinitialisation vous a été envoyé."));
    }

    @PostMapping("/reset-password-form")
    public ResponseEntity<String> resetPasswordForm(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("password");

       // userService.resetPassword(token, newPassword);
        return userService.resetPassword(token, newPassword);
    }


    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token) {
        Optional<User> user = userService.getUserByResetToken(token);
        if (user.isPresent()) {
            return ResponseEntity.status(302).location(URI.create("http://localhost:4200/reset-password-form?token=" + token)).build();
        } else {
            return ResponseEntity.status(400).body("Token invalide ou expiré.");
        }
    }
}
