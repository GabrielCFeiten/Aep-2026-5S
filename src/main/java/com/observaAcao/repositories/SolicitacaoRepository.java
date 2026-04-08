package com.observaAcao.repositories;

import com.observaAcao.configuracaoDB.ConnectionFactory;
import com.observaAcao.enums.*;
import com.observaAcao.models.SolicitacaoModel;

import java.sql.*;

public class SolicitacaoRepository {

    public SolicitacaoModel salvar(SolicitacaoModel s) {

        String sql = "INSERT INTO solicitacao " +
                "(categoria, descricao, endereco, bairro, prioridade, prazo, usuario_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getCategoria().name());
            ps.setString(2, s.getDescricao());
            ps.setString(3, s.getEndereco());
            ps.setString(4, s.getBairro());
            ps.setString(5, s.getPrioridade().name());
            ps.setDate(6, Date.valueOf(s.getPrazo()));
            ps.setInt(7, s.getUsuarioId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int protocolo = rs.getInt(1);
                return buscarPorId(protocolo);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }

        return null;
    }

    public SolicitacaoModel buscarPorId(int protocolo) {

        String sql = "SELECT * FROM solicitacao WHERE protocolo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, protocolo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SolicitacaoModel s = new SolicitacaoModel(
                        CategoriaEnum.valueOf(rs.getString("categoria")),
                        rs.getString("descricao"),
                        rs.getString("bairro"),
                        rs.getString("endereco")
                );

                s.setProtocolo(rs.getInt("protocolo"));
                s.setStatus(StatusEnum.valueOf(rs.getString("status")));
                s.setPrazo(rs.getDate("prazo").toLocalDate());
                s.setUsuarioId(rs.getInt("usuario_id"));

                return s;
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar: " + e.getMessage());
        }

        return null;
    }
}