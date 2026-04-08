package com.observaAcao.services;

import com.observaAcao.enums.TipoUsuarioEnum;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.repositories.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repo = new UsuarioRepository();

    public UsuarioModel criar(String nome, TipoUsuarioEnum tipo) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome inválido");
        }

        return repo.salvar(new UsuarioModel(nome, tipo));
    }
}