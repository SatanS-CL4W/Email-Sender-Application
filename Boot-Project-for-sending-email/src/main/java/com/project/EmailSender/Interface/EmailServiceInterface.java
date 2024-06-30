package com.project.EmailSender.Interface;

import java.io.File;
import java.io.InputStream;

public interface EmailServiceInterface {

    // Sending email to single person
    void sendEmailToSingle(String To, String Subject, String Message);

    //Sending email to multiple person
    void sendEmailToMultiple(String[] To, String Subject, String Message);

    //Sending email with HTML
    void sendEmailWithHtml(String To, String Subject, String htmlContent);

    //Sending email with attachment
    void sendEmailWithAttachment(String To, String Subject, String Message, File file);

    //Sending email with file attachment dynamically

    void SendEmailWithAttachmentInputStream(String to, String subject, String message, InputStream is);
}
