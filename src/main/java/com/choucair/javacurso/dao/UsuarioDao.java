package com.choucair.javacurso.dao;


import com.choucair.javacurso.models.Usuario;

import java.util.List;
public interface UsuarioDao {

    List<Usuario> getUsuarios();

    void Eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
