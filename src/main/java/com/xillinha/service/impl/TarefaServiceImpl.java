package com.xillinha.service.impl;

import com.xillinha.domain.model.Tarefa;
import com.xillinha.domain.model.Usuario;
import com.xillinha.domain.repository.TarefaRepository;
import com.xillinha.domain.repository.UsuarioRepository;
import com.xillinha.service.TarefaService;
import com.xillinha.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioService usuarioService;

    public TarefaServiceImpl(TarefaRepository tarefaRepository, UsuarioService usuarioService) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Tarefa findById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Tarefa> findAll() {
        return this.tarefaRepository.findAll();
    }

    @Override
    public Tarefa create(Tarefa novaTarefa) {
        if (novaTarefa == null) {
            throw new IllegalArgumentException("A tarefa a ser criada não pode ser nula.");
        }

        if (novaTarefa.getUsuario() == null) {
            throw new IllegalArgumentException("O usuário da tarefa a ser criada não pode ser nulo.");
        }

        return tarefaRepository.save(novaTarefa);
    }

    @Override
    public Tarefa update(Long id, Tarefa tarefa) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Tarefa> findByUsuario(Long id) {
        Usuario usuario = usuarioService.findById(id);

        List<Tarefa> tarefas = tarefaRepository.findByUsuario(usuario);

        return tarefas;
    }
}
