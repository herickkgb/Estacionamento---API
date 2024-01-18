package com.herick.demoparkapi.web.controllers;

import com.herick.demoparkapi.web.dto.UsuarioCreateDTO;
import com.herick.demoparkapi.web.dto.UsuarioDTO;
import com.herick.demoparkapi.entities.Usuario;
import com.herick.demoparkapi.services.UsuarioServicie;
import com.herick.demoparkapi.web.dto.UsuarioResponseDTO;
import com.herick.demoparkapi.web.dto.UsuarioSenhaDTO;
import com.herick.demoparkapi.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioServicie service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        Usuario user = service.create(UsuarioMapper.toUsuario(createDTO));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(UsuarioMapper.toDTO(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        Usuario user = service.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDTO dto) {
        Usuario user = service.updatePassword(id, dto.getSenhaAtual(),dto.getNovaSenha(),dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<Usuario> users = service.findAll();
        return ResponseEntity.ok(UsuarioMapper.toListDTO(users));
    }
}

