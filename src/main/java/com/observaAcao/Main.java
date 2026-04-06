package com.observaAcao;

import com.observaAcao.configuracaoDB.DBInit;
import com.observaAcao.controllers.UsuarioController;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.enums.TipoUsuarioEnum;

import java.util.Scanner;

import static com.observaAcao.controllers.LeituraController.lerInt;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioController userController = new UsuarioController();
        DBInit.init();

        System.out.println("Nome:");
        String nome = sc.nextLine();

        UsuarioModel user;

        while (true) {
            try {
                System.out.println("1 - Cidadão | 2 - Gestor");
                int tipo = lerInt(sc);

                user = new UsuarioModel(
                        nome,
                        TipoUsuarioEnum.fromCodigo(tipo)
                );

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Tente novamente.");
            }
        }

        userController.menuController(user, sc);

        sc.close();
    }
}