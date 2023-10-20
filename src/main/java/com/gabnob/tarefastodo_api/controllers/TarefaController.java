package com.gabnob.tarefastodo_api.controllers;

import com.gabnob.tarefastodo_api.entities.tarefas.dto.AtualizarTarefaDTO;
import com.gabnob.tarefastodo_api.entities.tarefas.dto.CriarTarefaDTO;
import com.gabnob.tarefastodo_api.entities.tarefas.Tarefa;
import com.gabnob.tarefastodo_api.entities.tarefas.dto.DetalheTarefaDTO;
import com.gabnob.tarefastodo_api.entities.tarefas.dto.ListarTarefasDTO;
import com.gabnob.tarefastodo_api.entities.usuarios.Usuario;
import com.gabnob.tarefastodo_api.infra.services.TarefaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalheTarefaDTO> criarTarefa(
            @RequestBody @Valid CriarTarefaDTO dto,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario usuarioPrincipal)
    {
        Long userId = usuarioPrincipal.getId();
        Tarefa tarefa = new Tarefa(dto);
        tarefaService.cadastrarTarefaDoUsuario(tarefa, userId);
        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalheTarefaDTO(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<ListarTarefasDTO>> listarTarefas(@AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        List<ListarTarefasDTO> lista = tarefaService.listarTodasAsTarefasDoUsuario(userId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tarefasNaoConcluidas")
    public ResponseEntity<List<ListarTarefasDTO>> listarTarefasNaoConcluidas(@AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        List<ListarTarefasDTO> lista = tarefaService.listarTodasTarefasNaoConcluidasDoUsuario(userId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tarefasConcluidas")
    public ResponseEntity<List<ListarTarefasDTO>> listarTarefasConcluidas(@AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        List<ListarTarefasDTO> lista = tarefaService.listarTodasTarefasConcluidasDoUsuario(userId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTarefaDTO> detalharTarefa(@PathVariable Integer tarefaId, @AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        Tarefa tarefa = tarefaService.obterTarefaDoUsuarioPorId(tarefaId, userId);
        return ResponseEntity.ok(new DetalheTarefaDTO(tarefa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalheTarefaDTO> atualizarTarefa(
            @RequestBody AtualizarTarefaDTO dto,
            @PathVariable Integer tarefaId,
            @AuthenticationPrincipal Usuario usuarioPrincipal)
    {
        Long userId = usuarioPrincipal.getId();
        Tarefa tarefa = tarefaService.obterTarefaDoUsuarioPorId(tarefaId, userId);
        tarefa.atualizarTarefa(dto);

        return ResponseEntity.ok(new DetalheTarefaDTO(tarefa));
    }

    @PutMapping("concluirTarefa/{id}")
    @Transactional
    public ResponseEntity<Void> concluirTarefa(@PathVariable Integer tarefaId, @AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        Tarefa tarefa = tarefaService.obterTarefaDoUsuarioPorId(tarefaId, userId);
        tarefa.concluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("desconcluirTarefa/{id}")
    @Transactional
    public ResponseEntity<Void> desfazerTarefa(@PathVariable Integer tarefaId, @AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        Tarefa tarefa = tarefaService.obterTarefaDoUsuarioPorId(tarefaId, userId);
        tarefa.desfazer();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Integer tarefaId, @AuthenticationPrincipal Usuario usuarioPrincipal) {
        Long userId = usuarioPrincipal.getId();
        tarefaService.excluirTarefaDoUsuarioPorId(tarefaId, userId);
        return ResponseEntity.noContent().build();
    }

}
