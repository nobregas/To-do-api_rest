package com.gabnob.tarefastodo_api.infra.services;

import com.gabnob.tarefastodo_api.entities.tarefas.Tarefa;
import com.gabnob.tarefastodo_api.entities.tarefas.dto.ListarTarefasDTO;
import com.gabnob.tarefastodo_api.entities.usuarios.Usuario;
import com.gabnob.tarefastodo_api.infra.repository.TarefaRepository;
import com.gabnob.tarefastodo_api.infra.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ListarTarefasDTO> listarTodasAsTarefasDoUsuario(Long userId) {
        return tarefaRepository.findAllByUsuarioId(userId).stream().map(ListarTarefasDTO::new).toList();
    }

    public List<ListarTarefasDTO> listarTodasTarefasNaoConcluidasDoUsuario(Long userId) {
        return tarefaRepository.findAllByUsuarioIdAndConcluidaFalse(userId).stream().map(ListarTarefasDTO::new).toList();
    }

    public List<ListarTarefasDTO> listarTodasTarefasConcluidasDoUsuario(Long userId) {
        return tarefaRepository.findAllByUsuarioIdAndConcluidaTrue(userId).stream().map(ListarTarefasDTO::new).toList();
    }

    public Tarefa cadastrarTarefaDoUsuario(Tarefa tarefa, Long userId) {
        tarefa.setUsuario(usuarioRepository.getReferenceById(userId));
        return tarefaRepository.save(tarefa);
    }

    public Tarefa obterTarefaDoUsuarioPorId(Integer tarefaId, Long userId) {
        return tarefaRepository.findByIdAndUsuarioId(tarefaId, userId);
    }

    public void excluirTarefaDoUsuarioPorId(Integer tarefaId,Long userId) {
        tarefaRepository.deleteByIdAndUsuarioId(tarefaId, userId);
    }

}
