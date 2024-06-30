package com.project.EmailSender;

import com.project.EmailSender.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        System.out.println("Sending Email...");
        emailService.sendEmailToSingle("kumarraunak42003@gmail.com", "Sending Mail Demo", "Hello Mr. Kumar, This is a sample email for Practice");
    }

    @Test
    void sendHtmlContent() {

        String html = ""
                + "<h1 style='color:red; border:1px solid red'>Welcome Kumar Raunak</h1>" +
                "";

        emailService.sendEmailWithHtml("kumarraunak42003@gmail.com", "Email from spring boot", html);
    }

    @Test
    void sendMailWithAttachment() {
        emailService.sendEmailWithAttachment("kumarraunak42003@gmail.com",
                "Email with file",
                "This email contains attachments",
                new File("F:\\Spring Boot\\Boot Project for sending email\\Boot-Project-for-sending-email\\src\\main\\resources\\static\\images\\lake.jpg"));
    }

    @Test
    void sendEmailWithFile() {
        File file = new File("F:\\Spring Boot\\Boot Project for sending email\\Boot-Project-for-sending-email\\src\\main\\resources\\static\\images\\lake.jpg");
        try {
            InputStream is = new FileInputStream(file);
            emailService.SendEmailWithAttachmentInputStream("kumarraunak42003@gmail.com", "Email having file",
                    "This Email contains file", is);

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
