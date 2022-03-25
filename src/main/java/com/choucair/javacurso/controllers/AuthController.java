package com.choucair.javacurso.controllers;

import com.choucair.javacurso.dao.UsuarioDao;
import com.choucair.javacurso.models.Usuario;
import com.choucair.javacurso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired(required=true)
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario usuarioLogged = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogged !=null){

           String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogged.getId()),usuarioLogged.getEmail());
            return tokenJwt;
        }else{
            return "Fallo";
        }
    }
}
