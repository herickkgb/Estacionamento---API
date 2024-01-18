package com.herick.demoparkapi.web.dto;

import com.herick.demoparkapi.entities.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UsuarioDTO {

    private Long id;
    private String userName;
    private String password;
    private Usuario.Role role;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private String criadoPor;
    private String modificadoPor;

    public UsuarioDTO(Long id, String userName, String password, Usuario.Role role, LocalDateTime dataCriacao,
                      LocalDateTime dataModificacao, String criadoPor, String modificadoPor) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.criadoPor = criadoPor;
        this.modificadoPor = modificadoPor;
    }

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.dataCriacao = entity.getDataCriacao();
        this.dataModificacao = entity.getDataModificacao();
        this.criadoPor = entity.getCriadoPor();
        this.modificadoPor = entity.getModificadoPor();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Usuario.Role getRole() {
        return role;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }
}
