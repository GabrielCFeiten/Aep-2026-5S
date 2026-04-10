package com.observaAcao.services;

import com.observaAcao.enums.TipoUsuarioEnum;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.repositories.UsuarioRepository;



public class UsuarioService {

    private final UsuarioRepository repo = new UsuarioRepository();

    public UsuarioModel criar(String nome, TipoUsuarioEnum tipo, String cpf, String telefone) {

        if (tipo != TipoUsuarioEnum.ANONIMO) {

            if (nome == null || nome.isBlank())
                throw new RuntimeException("Nome obrigatório");

        }

        return repo.salvar(new UsuarioModel(nome,telefone, cpf,tipo));
    }
}