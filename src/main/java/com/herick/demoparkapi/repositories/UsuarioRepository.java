package com.herick.demoparkapi.repositories;

import com.herick.demoparkapi.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
