package com.herick.demoparkapi.services;

import com.herick.demoparkapi.web.dto.UsuarioDTO;
import com.herick.demoparkapi.entities.Usuario;
import com.herick.demoparkapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServicie {

    private final UsuarioRepository repository;

    @Transactional
    public Usuario create(Usuario user) {
        return repository.save(user);
    }


    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário nao encontardo: " + id)
        );
    }


    @Transactional
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    private void copyToDto(UsuarioDTO user, Usuario entity) {
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        entity.setUserName(user.getUserName());
    }

    @Transactional
    public Usuario updatePassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova Senha não confere com confirmação de senha");
        }
        Usuario user = findById(id);
        if (!user.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("SUA SENHA NÃO CONFERE");
        }
        user.setPassword(novaSenha);
        return user;

    }
}
