package com.xillinha.controller;

import com.xillinha.domain.model.Tarefa;
import com.xillinha.domain.model.Usuario;
import com.xillinha.service.TarefaService;
import com.xillinha.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    @Operation(summary = "Buscar todos as tarefas", description = "Buscar uma lista de todas as tarefas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso")
    })
    public ResponseEntity<List<Tarefa>> findAll() {
        var tarefas = tarefaService.findAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/user/{idusuario}")
    @Operation(summary = "Buscar todos as tarefas do usuário", description = "Buscar uma lista de todas as tarefas cadastradas para o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso")
    })
    public ResponseEntity<List<Tarefa>> findByUsuario(@PathVariable Long idusuario) {
        var tarefas = tarefaService.findByUsuario(idusuario);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Recuperar uma tarefa específica baseado no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Tarefa> findById(@PathVariable Long id) {

        var tarefa = tarefaService.findById(id);

        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma tarefa", description = "Atualizar os dados de uma tarefa existente baseado em seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos da tarefa fornecida")
    })
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        var tarefaAtualizado = tarefaService.update(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizado);
    }

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa", description = "Criar uma nova tarefa e retornar os dados da tarefa criada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos da tarefa fornecida")
    })
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa novaTarefa) {

        var tarefaCriada = tarefaService.create(novaTarefa);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefaCriada.getId()).toUri();

        return ResponseEntity.created(location).body(tarefaCriada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa", description = "Deletar uma tarefa existente baseado em seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}