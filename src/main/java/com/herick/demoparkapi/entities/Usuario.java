package com.herick.demoparkapi.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String userName;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "role", nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CLIENTE;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @Column(name = "criado_por")
    private String criadoPor;
    @Column(name = "modificado_por")
    private String modificadoPor;


    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                ", criadoPor='" + criadoPor + '\'' +
                ", modificadoPor='" + modificadoPor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
