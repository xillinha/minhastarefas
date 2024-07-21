package com.xillinha.controller;

import com.xillinha.domain.model.Usuario;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Recuperar um usuário específico baseado no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {

        var usuario = usuarioService.findById(id);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Criar um novo usuário e retornar os dados do usuário criado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos do usuário fornecido")
    })
    public ResponseEntity<Usuario> create(@RequestBody Usuario novoUsuario) {

        var usuarioCriado = usuarioService.create(novoUsuario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getId()).toUri();

        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os usuários", description = "Buscar uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso")
    })
    public ResponseEntity<List<Usuario>> findAll() {
        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Atualizar os dados de um usuário existente baseado em seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos do usuário fornecido")
    })
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        var usuarioAtualizado = usuarioService.update(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário", description = "Deletar um usuário existente baseado em seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
