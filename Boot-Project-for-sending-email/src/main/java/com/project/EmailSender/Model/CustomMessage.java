package com.project.EmailSender.Model;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomMessage {
    private String message;

    private HttpStatus httpStatus;

    private boolean success = false;
}
