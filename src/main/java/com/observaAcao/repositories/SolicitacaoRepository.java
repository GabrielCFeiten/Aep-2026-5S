package com.observaAcao.repositories;

import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.enums.*;
import com.observaAcao.configuracaoDB.ConnectionFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SolicitacaoRepository {

    public void salvar(SolicitacaoModel s) {

        String sql = "INSERT INTO solicitacao (protocolo, categoria, descricao, localizacao, prioridade, status, prazo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, s.getProtocolo());
            ps.setString(2, s.getCategoria());
            ps.setString(3, s.getDescricao());
            ps.setString(4, s.getLocalizacao());
            ps.setString(5, s.getPrioridade().name());
            ps.setString(6, s.getStatus().name());
            ps.setDate(7, Date.valueOf(s.getPrazo()));

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }
    }

//    public List<SolicitacaoModel> listar() {
//
//        List<SolicitacaoModel> lista = new ArrayList<>();
//
//        String sql = "SELECT * FROM solicitacao";
//
//        try (Connection c = ConnectionFactory.getConnection();
//             Statement st = c.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//
//            while (rs.next()) {
//
//                SolicitacaoModel s = new SolicitacaoModel(
//                        rs.getString("protocolo"),
//                        rs.getString("categoria"),
//                        rs.getString("descricao"),
//                        rs.getString("localizacao"),
//                        enums.PrioridadeEnum.valueOf(rs.getString("prioridade")),
//                        enums.StatusEnum.valueOf(rs.getString("status")),
//                        rs.getDate("prazo").toLocalDate()
//                );
//
//                lista.add(s);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao listar: " + e.getMessage());
//        }
//
//        return lista;
//    }
}
