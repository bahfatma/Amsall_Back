package com.Amsall.amsallApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String toEmail, String resetToken) {
        String subject = "Réinitialisation de votre mot de passe";
        String resetUrl ="http://localhost:4200/reset-password-form?token=" + resetToken;
        String message = "Bonjour,\n\nCliquez sur le lien suivant pour réinitialiser votre mot de passe :\n"
                + resetUrl + "\n\nSi vous n'avez pas demandé cette réinitialisation, ignorez ce message.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
