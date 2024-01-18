package com.herick.demoparkapi.web.dto.mapper;

import com.herick.demoparkapi.entities.Usuario;
import com.herick.demoparkapi.web.dto.UsuarioCreateDTO;
import com.herick.demoparkapi.web.dto.UsuarioDTO;
import com.herick.demoparkapi.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDTO createDTO) {
        return new ModelMapper().map(createDTO, Usuario.class);
    }

    public static UsuarioResponseDTO toDTO(Usuario create) {
        String role = create.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(create, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }
}
