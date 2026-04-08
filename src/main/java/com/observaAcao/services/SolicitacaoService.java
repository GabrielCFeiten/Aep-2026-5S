package com.observaAcao.services;

import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.repositories.SolicitacaoRepository;

public class SolicitacaoService {

    private final SolicitacaoRepository banco = new SolicitacaoRepository();

    public SolicitacaoModel criar(CategoriaEnum categoria,
                                  String descricao,
                                  String bairro,
                                  String endereco,
                                  UsuarioModel usuario) {

        if (descricao == null || descricao.trim().length() < 10) {
            throw new RuntimeException("Descrição deve ter no mínimo 10 caracteres");
        }

        SolicitacaoModel s = new SolicitacaoModel(
                categoria,
                descricao,
                bairro,
                endereco
        );

        s.setUsuarioId(usuario.getId());

        return banco.salvar(s);
    }
}