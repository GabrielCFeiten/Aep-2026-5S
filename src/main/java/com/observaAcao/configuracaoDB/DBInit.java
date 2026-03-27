package com.observaAcao.configuracaoDB;

import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void init() {
        try (Connection c = ConnectionFactory.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                CREATE TABLE IF NOT EXISTS solicitacao (
                    protocolo VARCHAR PRIMARY KEY,
                    categoria VARCHAR,
                    descricao TEXT,
                    localizacao VARCHAR,
                    prioridade VARCHAR,
                    status VARCHAR,
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
