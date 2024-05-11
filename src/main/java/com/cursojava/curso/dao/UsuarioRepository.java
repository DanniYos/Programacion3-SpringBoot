package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario obtenerUsuarioPorCredenciales(String usuario);
}
