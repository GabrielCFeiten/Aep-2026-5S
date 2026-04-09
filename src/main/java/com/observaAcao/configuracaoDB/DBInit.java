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
                    nome VARCHAR(255),
                    tipo VARCHAR(50),
                    cpf VARCHAR(15),
                    telefone VARCHAR(20)
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

            s.execute("""
                CREATE TABLE IF NOT EXISTS historico (
                id INT AUTO_INCREMENT PRIMARY KEY,
                protocolo INT,
                status VARCHAR(50),
                data_movimentacao TIMESTAMP,
                responsavel_id INT,
                justificativa VARCHAR(255),
        
                FOREIGN KEY (protocolo) REFERENCES solicitacao(protocolo),
                FOREIGN KEY (responsavel_id) REFERENCES usuario(id)
                )   
            """);

        } catch (Exception e) {
            System.out.println("Erro DB: " + e.getMessage());
        }
    }
}
