package com.gabnob.tarefastodo_api.entities.tarefas.dto;

import com.gabnob.tarefastodo_api.entities.tarefas.Tarefa;

import java.time.LocalDate;

public record DetalheTarefaDTO(
        Integer id,
        String titulo,
        String descricao,
        Boolean concluida,
        LocalDate datacriacao,
        LocalDate dataconclusao) {

    public DetalheTarefaDTO(Tarefa tarefa) {
        this(tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getConcluida(),
                tarefa.getDatacriacao(),
                tarefa.getDataconclusao());
    }

}
