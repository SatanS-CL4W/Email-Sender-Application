package com.project.EmailSender.Service;

import com.project.EmailSender.Interface.EmailServiceInterface;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailService implements EmailServiceInterface {

    @Autowired
    private JavaMailSender mailSender;

//    private Logger logger = (Logger) LoggerFactory.getLogger(EmailService.class);

    @Override
    public void sendEmailToSingle(String To, String Subject, String Message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("kumarroman1230@gmail.com");
        simpleMailMessage.setTo(To);
        simpleMailMessage.setSubject(Subject);
        simpleMailMessage.setText(Message);

        mailSender.send(simpleMailMessage);
//        logger.info("Email sent successfully...");
    }

    @Override
    public void sendEmailToMultiple(String[] To, String Subject, String Message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("kumarroman1230@gmail.com");
        simpleMailMessage.setTo(To);
        simpleMailMessage.setSubject(Subject);
        simpleMailMessage.setText(Message);

        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(String To, String Subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(To);
            helper.setText(htmlContent,true);
            helper.setSubject(Subject);
            helper.setFrom("kumarroman1230@gmail.com");

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithAttachment(String To, String Subject, String Message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(To);
            helper.setText(Message);
            helper.setSubject(Subject);
            helper.setFrom("kumarroman1230@gmail.com");

            FileSystemResource fileSystemResource = new FileSystemResource(file);

            helper.addAttachment(fileSystemResource.getFilename(),file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void SendEmailWithAttachmentInputStream(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setText(message);
            helper.setSubject(subject);
            helper.setFrom("kumarroman1230@gmail.com");

            File file = new File("src/main/resources/email/test.png");

            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            FileSystemResource fileSystemResource = new FileSystemResource(file);

            helper.addAttachment(fileSystemResource.getFilename(),file);

            mailSender.send(mimeMessage);

            System.out.println("Email sent successfully!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
