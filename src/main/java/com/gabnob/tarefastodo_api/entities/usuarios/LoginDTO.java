package com.gabnob.tarefastodo_api.entities.usuarios;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String username,
        @NotBlank
        String senha) {
}
