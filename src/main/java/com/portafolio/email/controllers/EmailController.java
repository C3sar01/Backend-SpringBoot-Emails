package com.portafolio.email.controllers;

import com.portafolio.email.models.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${GMAIL_PASSWORD}")
    private String emailPassword;


    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            // Construir el correo electrónico utilizando los datos de emailRequest
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo("cesar.cssoto@gmail.com");
            helper.setSubject("Nuevo mensaje de " + emailRequest.getName());

            String htmlContent = "<html>" +
                    "<head>" +
                    "<style>" +
                    ".container {" +
                    "  max-width: 600px;" +
                    "  margin: 0 auto;" +
                    "  padding: 20px;" +
                    "  background-color: #f9f9f9;" +
                    "}" +
                    ".title {" +
                    "  text-align: center;" +
                    "  font-size: 28px;" +
                    "  color: #333;" +
                    "}" +
                    ".details {" +
                    "  padding: 20px;" +
                    "  margin-top: 30px;" +
                    "  background-color: #ffffff;" +
                    "  border-radius: 4px;" +
                    "  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);" +
                    "}" +
                    ".details p {" +
                    "  font-size: 18px;" +
                    "  margin: 10px 0;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"container\">" +
                    "<h1 class=\"title\">Nuevo mensaje recibido</h1>" +
                    "<div class=\"details\">" +
                    "<p><strong>Nombre:</strong> " + emailRequest.getName() + "</p>" +
                    "<p><strong>Email:</strong> " + emailRequest.getEmail() + "</p>" +
                    "<p><strong>Mensaje:</strong></p>" +
                    "<p>" + emailRequest.getMessage() + "</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);

            // Enviar el correo electrónico
            javaMailSender.send(message);

            return ResponseEntity.ok("Correo electrónico enviado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el correo electrónico.");
        }
    }
}
