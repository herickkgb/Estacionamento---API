package com.herick.demoparkapi.repositories;

import com.herick.demoparkapi.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUserName(String username);

    @Query("Select u.role from Usuario u where u.userName like:username")
    Usuario.Role findRoleByUsername(String username);
}
