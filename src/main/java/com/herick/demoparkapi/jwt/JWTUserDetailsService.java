package com.herick.demoparkapi.jwt;

import com.herick.demoparkapi.entities.Usuario;
import com.herick.demoparkapi.services.UsuarioServici;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private final UsuarioServici usuarioServici;

    public JWTUserDetailsService(UsuarioServici usuarioServici) {
        this.usuarioServici = usuarioServici;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioServici.buscarPorUsername(username);
        return new JWTUserDetails(usuario);
    }

    public JWTToken getTokenAthenticated(String username) {
        Usuario.Role role = usuarioServici.buscarRolePorUsername(username);
        return JWTUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
