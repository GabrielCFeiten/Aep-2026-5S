package com.observaAcao.configuracaoDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static Connection getConnection() {
        String url = "jdbc:h2:~/observaAcao";
        String user = "root";
        String senha = "";

        try {
            return DriverManager.getConnection(url, user, senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro conexão: " + e.getMessage());
        }
    }
}