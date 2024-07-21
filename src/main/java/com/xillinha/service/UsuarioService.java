package com.xillinha.service;

import com.xillinha.domain.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Long id);

    List<Usuario> findAll();

    Usuario create(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);
}
