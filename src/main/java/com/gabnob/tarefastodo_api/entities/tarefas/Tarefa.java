package com.gabnob.tarefastodo_api.entities.tarefas;


import com.gabnob.tarefastodo_api.entities.tarefas.dto.AtualizarTarefaDTO;
import com.gabnob.tarefastodo_api.entities.tarefas.dto.CriarTarefaDTO;
import com.gabnob.tarefastodo_api.entities.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity(name="tarefas")
@Table(name="tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of="id")
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private Boolean concluida; //True = feito, False = nao concluido
    private LocalDate datacriacao;
    private LocalDate dataconclusao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Tarefa(CriarTarefaDTO dto) {
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.concluida = false;
        this.datacriacao = LocalDate.now();
    }

    public void atualizarTarefa(AtualizarTarefaDTO dto) {
        if ((dto.titulo() != null) && (dto.titulo() != "")) {
            this.titulo = dto.titulo();
        }
        if ((dto.descricao() != null) && (dto.descricao() != "")) {
            this.descricao = dto.descricao();
        }
        this.datacriacao = LocalDate.now();
    }

    public void concluir() {
        this.setConcluida(true);
        this.dataconclusao = LocalDate.now();
    }

    public void desfazer() {
        this.setConcluida(false);
        this.dataconclusao = null;
    }

}
