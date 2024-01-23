package com.herick.demoparkapi.jwt;

import com.herick.demoparkapi.entities.Usuario;
import com.herick.demoparkapi.services.UsuarioServicie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTUserDetailsService implements UserDetailsService {
    private final UsuarioServicie usuarioServicie;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioServicie.buscarPorUsername(username);
        return new JWTUserDetails(usuario);
    }

    public JWTToken getTokenAthenticated(String username) {
        Usuario.Role role = usuarioServicie.buscarRolePorUsername(username);
        return JWTUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
