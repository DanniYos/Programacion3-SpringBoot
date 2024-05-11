package com.cursojava.curso.services;

import com.cursojava.curso.dao.UsuarioRepository;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioDao;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    public void eliminar(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public void registrar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        Usuario user = usuarioDao.obtenerUsuarioPorCredenciales(usuario.getEmail());

        String passwordHashed = user.getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return user;
        }
        return null;
    }
}
