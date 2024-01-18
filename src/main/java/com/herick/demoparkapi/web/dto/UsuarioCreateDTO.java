package com.herick.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Formato do e-mail está invalido")
    private String userName;

    @NotBlank
    @Size(min = 6, max = 6,message = "A senha deve conter 6 Caracteres")
    private String password;
}
