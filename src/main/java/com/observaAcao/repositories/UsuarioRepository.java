package com.observaAcao.repositories;

import com.observaAcao.models.UsuarioModel;
import com.observaAcao.configuracaoDB.ConnectionFactory;

import java.sql.*;

public class UsuarioRepository {

    public UsuarioModel salvar(UsuarioModel u) {

        String sql = "INSERT INTO usuario (nome, tipo, cpf, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getTipo().name());
            ps.setString(3, u.getCpf());
            ps.setString(4, u.getTelefone());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int idGerado = rs.getInt(1);
                u.setId(idGerado);
            }

            return u;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}