package com.gabnob.tarefastodo_api.entities.tarefas.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarTarefaDTO(
        @NotBlank
        String titulo,
        String descricao)
{

}
