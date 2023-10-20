package com.gabnob.tarefastodo_api.entities.tarefas.dto;

import com.gabnob.tarefastodo_api.entities.tarefas.Tarefa;

import java.time.LocalDate;

public record ListarTarefasDTO(
        Integer id,
        String titulo,
        Boolean status,
        LocalDate datacriacao,
        LocalDate dataconclusao)
{
    public ListarTarefasDTO(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getConcluida(),
                tarefa.getDatacriacao(),
                tarefa.getDataconclusao()
        );
    }

}
