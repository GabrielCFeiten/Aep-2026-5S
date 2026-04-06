package com.observaAcao.repositories;

import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.enums.*;
import com.observaAcao.configuracaoDB.ConnectionFactory;

import java.sql.*;
import java.util.*;

public class SolicitacaoRepository {

    public void salvar(SolicitacaoModel s) {

        String sql = "INSERT INTO solicitacao (categoria, descricao, localizacao) VALUES (?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getCategoria());
            ps.setString(2, s.getDescricao());
            ps.setString(3, s.getLocalizacao());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }
    }

}
