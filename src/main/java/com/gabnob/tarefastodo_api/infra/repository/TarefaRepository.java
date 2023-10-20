package com.gabnob.tarefastodo_api.infra.repository;

import com.gabnob.tarefastodo_api.entities.tarefas.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findAllByUsuarioIdAndConcluidaTrue(Long userId);

    List<Tarefa> findAllByUsuarioIdAndConcluidaFalse(Long userId);

    List<Tarefa> findAllByUsuarioId(Long userId);

    Tarefa findByIdAndUsuarioId(Integer tarefaId, Long userId);

    void deleteByIdAndUsuarioId(Integer tarefaId, Long userId);
}
