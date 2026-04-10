package com.observaAcao;

import com.observaAcao.configuracaoDB.DBInit;
import com.observaAcao.controllers.UsuarioController;
import com.observaAcao.models.UsuarioModel;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DBInit.init();

        UsuarioController usuarioController = new UsuarioController();

        // =========================
        // CRIAÇÃO DO USUÁRIO
        // =========================
        UsuarioModel usuario = usuarioController.criarUsuario(sc);

        // =========================
        // EXECUTA MENU PELO TIPO
        // =========================
        usuario.getTipo().executarMenu(sc, usuario);

        sc.close();
    }
}