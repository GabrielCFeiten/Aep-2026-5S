package com.observaAcao.repositories;

import com.observaAcao.configuracaoDB.ConnectionFactory;
import com.observaAcao.enums.StatusEnum;
import com.observaAcao.models.HistoricoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoRepository {

    public void salvar(HistoricoModel h) {

        String sql = "INSERT INTO historico (protocolo, status, data_movimentacao, responsavel_id, justificativa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, h.getProtocolo());
            ps.setString(2, h.getStatus().name());
            ps.setTimestamp(3, Timestamp.valueOf(h.getData()));
            ps.setInt(4, h.getResponsavelId());
            ps.setString(5, h.getJustificativa());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar histórico");
        }
    }

    public void atualizarStatus(int protocolo, StatusEnum status) {

        String sql = "UPDATE solicitacao SET status = ? WHERE protocolo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ps.setInt(2, protocolo);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar status");
        }
    }

    public List<HistoricoModel> listarPorProtocolo(int protocolo) {

        List<HistoricoModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM historico WHERE protocolo = ? ORDER BY data_movimentacao";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, protocolo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HistoricoModel h = new HistoricoModel(
                        rs.getInt("protocolo"),
                        StatusEnum.valueOf(rs.getString("status")),
                        rs.getInt("responsavel_id"),
                        rs.getString("justificativa")
                );

                lista.add(h);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar histórico");
        }

        return lista;
    }

    public HistoricoModel buscarUltimoPorProtocolo(int protocolo) {

        String sql = """
            SELECT * FROM historico
            WHERE protocolo = ?
            ORDER BY data_movimentacao DESC
            LIMIT 1
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, protocolo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HistoricoModel(
                        rs.getInt("protocolo"),
                        StatusEnum.valueOf(rs.getString("status")),
                        rs.getInt("responsavel_id"),
                        rs.getString("justificativa")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar último histórico");
        }

        return null;
    }
}