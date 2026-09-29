package com.metadiaria.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroRespostaDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;

}
