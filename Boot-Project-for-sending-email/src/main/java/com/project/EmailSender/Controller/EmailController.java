package com.project.EmailSender.Controller;


import com.project.EmailSender.Model.CustomMessage;
import com.project.EmailSender.Model.EmailRequest;
import com.project.EmailSender.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(
                CustomMessage.builder().message("Email Sent Successfully!!")
                        .httpStatus(HttpStatus.OK).success(true).build()
        );
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomMessage> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) {
        try {
            emailService.SendEmailWithAttachmentInputStream(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());

            return ResponseEntity.ok(
                    CustomMessage.builder().message("Email Sent Successfully!!")
                            .httpStatus(HttpStatus.OK).success(true).build()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
