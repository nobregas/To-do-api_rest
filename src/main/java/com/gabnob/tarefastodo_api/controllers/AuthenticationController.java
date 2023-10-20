package com.gabnob.tarefastodo_api.controllers;


import com.gabnob.tarefastodo_api.entities.usuarios.LoginDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationManager manager;

    @Autowired
    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid LoginDTO dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.username(), dados.senha());
        var autenticacao = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
