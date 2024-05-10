package com.cursojava.curso.services;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @Override
    public void eliminar(Long id) {
        usuarioDao.eliminar(id);
    }

    @Override
    public void registrar(Usuario usuario) {
        usuarioDao.registrar(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        Usuario user = usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        String passwordHashed = user.getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return user;
        }
        return null;
    }
}
