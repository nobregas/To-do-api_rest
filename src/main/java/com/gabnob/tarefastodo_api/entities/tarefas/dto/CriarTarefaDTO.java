package com.gabnob.tarefastodo_api.entities.tarefas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CriarTarefaDTO(
        String titulo,
        String descricao)
{

}
