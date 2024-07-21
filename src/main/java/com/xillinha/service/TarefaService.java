package com.xillinha.service;

import com.xillinha.domain.model.Tarefa;

import java.util.List;

public interface TarefaService {

    Tarefa findById(Long id);

    List<Tarefa> findAll();

    Tarefa create(Tarefa tarefa);

    Tarefa update(Long id, Tarefa tarefa);

    void delete(Long id);

    List<Tarefa> findByUsuario(Long id);
}
