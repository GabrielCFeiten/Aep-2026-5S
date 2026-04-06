package com.observaAcao.configuracaoDB;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void init() {
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                CREATE TABLE IF NOT EXISTS solicitacao (
                protocolo INT PRIMARY KEY AUTO_INCREMENT,
                categoria VARCHAR(100),
                descricao VARCHAR(255),
                localizacao VARCHAR(150),
                prioridade VARCHAR(50),
                status VARCHAR(50) DEFAULT 'ABERTO',
                prazo DATE
                )
            """);

            s.execute("""
                CREATE TABLE IF NOT EXISTS usuario (
                    id IDENTITY PRIMARY KEY,
                    nome VARCHAR,
                    tipo VARCHAR
                )
            """);

        } catch (Exception e) {
            System.out.println("Erro DB: " + e.getMessage());
        }
    }
}
