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
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "<head>" +
                            "  <meta charset='UTF-8'>" +
                            "  <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                            "</head>" +
                            "<body style='margin: 0; padding: 0; background-color: #f6f6f6; font-family: Arial, sans-serif;'>"
                            +
                            "  <table role='presentation' cellpadding='0' cellspacing='0' width='100%'>" +
                            "    <tr>" +
                            "      <td align='center' style='padding: 40px 0;'>" +
                            "        <table width='600' style='background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); padding: 40px;'>"
                            +
                            "          <tr>" +
                            "            <td align='center'>" +
                            "              <h2 style='color: #2d2d2d;'><span style='color:#11181C;'>Neon SVE</span></h2>"
                            +
                            "              <p style='font-size: 16px; color: #555;'>Hola, tu cuenta ha sido creada exitosamente. Estas son tus credenciales de acceso:</p>"
                            +
                            "              <table style='margin: 20px 0; font-size: 16px; color: #333;'>" +
                            "                <tr><td><strong>Correo de acceso:</strong></td><td style='padding-left: 10px;'>"
                            + usuarioCorreo + "</td></tr>" +
                            "                <tr><td><strong>Contraseña:</strong></td><td style='padding-left: 10px;'>"
                            + claveSinCodificar + "</td></tr>" +
                            "              </table>" 
                            +
                            "              <p style='margin-top: 30px; font-size: 14px; color: #888;'>Por seguridad, te recomendamos cambiar tu contraseña en tu primer acceso.</p>"
                            +
                            "              <hr style='margin: 40px 0; border: none; border-top: 1px solid #eee;'>" +
                            "              <p style='font-size: 12px; color: #aaa;'>© 2025 Neon SVE. Todos los derechos reservados.<br>Av. Innovación 123, Lima - Perú</p>"
                            +
                            "            </td>" +
                            "          </tr>" +
                            "        </table>" +
                            "      </td>" +
                            "    </tr>" +
                            "  </table>" +
                            "</body>" +
                            "</html>",
                    true);

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}
