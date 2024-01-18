package com.herick.demoparkapi.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDTO {
    private Long id;
    private String userName;
    private String role;
}
