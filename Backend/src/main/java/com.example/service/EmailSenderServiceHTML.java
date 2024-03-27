package com.example.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailSenderServiceHTML {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithTemplate(String toEmail, String subject, String line) throws MessagingException, IOException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("flatinfo2024@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);

        // Template dosyasını oku
        String templateContent = readTemplateContent("MailTemplate.html");


        // Değişkenleri doldur ve içeriği ayarla
        String filledContent = fillTemplate(templateContent, line);
        helper.setText(filledContent, true); // true parametresi HTML içeriğini etkinleştirir

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    private String readTemplateContent(String templatePath) throws IOException {
        // Şablon dosyasını oku
        ClassPathResource resource = new ClassPathResource(templatePath);
        byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(contentBytes, StandardCharsets.UTF_8);
    }

    private String fillTemplate(String templateContent, String line) {
        // Şablon içeriğindeki değişkenleri doldur
        return templateContent.replace("{{ line }}", line);
    }
}
