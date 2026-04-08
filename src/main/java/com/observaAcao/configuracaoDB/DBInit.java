package com.observaAcao.configuracaoDB;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void init() {
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                CREATE TABLE IF NOT EXISTS usuario (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nome VARCHAR,
                    tipo VARCHAR
                )
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS solicitacao (
                protocolo INT PRIMARY KEY AUTO_INCREMENT,
                categoria VARCHAR(100),
                descricao VARCHAR(255),
                endereco VARCHAR(150),
                bairro VARCHAR(50),
                prioridade VARCHAR(50),
                status VARCHAR(50) DEFAULT 'ABERTO',
                prazo DATE,
                usuario_id INT,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id)
                )
            """);

        } catch (Exception e) {
            System.out.println("Erro DB: " + e.getMessage());
        }
    }
}
