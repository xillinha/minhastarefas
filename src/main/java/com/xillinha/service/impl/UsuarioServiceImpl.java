package com.xillinha.service.impl;

import com.xillinha.domain.model.Usuario;
import com.xillinha.domain.repository.UsuarioRepository;
import com.xillinha.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Optional.ofNullable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario create(Usuario novoUsuario) {

        if (novoUsuario == null) {
            throw new IllegalArgumentException("O usuário a ser criado não pode ser nulo.");
        }

        if (novoUsuario.getCpf() == null) {
            throw new IllegalArgumentException("O CPF do usuário a ser criado não pode ser nulo.");
        }

        if (usuarioRepository.existsByCpf(novoUsuario.getCpf())) {
            throw new IllegalArgumentException("Esse CPF de usuário já existe!");
        }

        return usuarioRepository.save(novoUsuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioBanco = this.findById(id);

        if (!usuarioBanco.getId().equals(usuario.getId())) {
            throw new IllegalArgumentException("O ID a ser atualizado deve ser igual ao ID do usuário gravado.");
        }

        usuarioBanco.setCpf(usuario.getCpf());
        usuarioBanco.setNome(usuario.getNome());

        return this.usuarioRepository.save(usuarioBanco);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = this.findById(id);
        this.usuarioRepository.delete(usuario);
    }
}
