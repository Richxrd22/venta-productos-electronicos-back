package com.neon.sve.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCredenciales(String para, String usuarioCorreo, String claveSinCodificar) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(para);
            helper.setSubject("Tus credenciales de acceso a Neon SVE");
            helper.setText(
                "<h3>Hola, bienvenido a Neon</h3>" +
                "<p>Tu usuario ha sido creado exitosamente. Aquí tienes tus credenciales:</p>" +
                "<ul>" +
                "<li><strong>Correo de acceso:</strong> " + usuarioCorreo + "</li>" +
                "<li><strong>Contraseña:</strong> " + claveSinCodificar + "</li>" +
                "</ul>" +
                "<p>Te recomendamos cambiar tu contraseña en el primer ingreso.</p>", true);

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}
