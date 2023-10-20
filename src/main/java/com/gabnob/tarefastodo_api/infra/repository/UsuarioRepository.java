package com.gabnob.tarefastodo_api.infra.repository;

import com.gabnob.tarefastodo_api.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
