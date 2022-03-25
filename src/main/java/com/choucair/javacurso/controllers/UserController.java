package com.choucair.javacurso.controllers;


import com.choucair.javacurso.dao.UsuarioDao;
import com.choucair.javacurso.models.Usuario;
import com.choucair.javacurso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Miguel");
        usuario.setApellido("Rubide");
        usuario.setEmail("mima@gmail.com");
        usuario.setTelefono("123456789");
        usuario.setPassword("124536");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
        if(!validarToken(token)){return null;}
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
     public void registrar(@RequestBody Usuario usuario) {
        //Encriptar la contraseña
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      // String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(argon2.hash(1,1024,1,usuario.getPassword()));

        usuarioDao.registrar(usuario);
     }

    @RequestMapping(value = "usuarioEditar")
    public Usuario editar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        usuario.setApellido("Rubide");
        usuario.setEmail("mima@gmail.com");
        usuario.setTelefono("123456789");
        usuario.setPassword("124536");
        return usuario;
    }
    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void  eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
        if(!validarToken(token)){return;}
        usuarioDao.Eliminar(id);
    }

}
