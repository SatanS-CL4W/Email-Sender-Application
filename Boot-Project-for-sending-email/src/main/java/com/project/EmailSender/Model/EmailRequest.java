package com.project.EmailSender.Model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {

    private String to;

    private String message;

    private String Subject;

}
