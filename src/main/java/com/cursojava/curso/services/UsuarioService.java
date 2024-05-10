package com.cursojava.curso.services;

import com.cursojava.curso.models.Usuario;

import java.util.List;




public interface UsuarioService {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
