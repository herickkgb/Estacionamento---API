package com.herick.demoparkapi.jwt;

import com.herick.demoparkapi.entities.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JWTUserDetails extends User {

    private Usuario user;

    public JWTUserDetails(Usuario usuario) {
        super(usuario.getUserName(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.user = usuario;
    }

    public Long getId() {
        return this.user.getId();
    }

    public String getRole() {
        return this.user.getRole().name();
    }
}
