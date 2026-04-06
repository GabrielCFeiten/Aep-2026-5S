package com.observaAcao;

import com.observaAcao.controllers.SolicitacaoController;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.enums.TipoUsuarioEnum;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SolicitacaoController controller = new SolicitacaoController();

        System.out.println("Nome:");
        String nome = sc.nextLine();

        System.out.println("1 - Cidadão | 2 - Gestor");
        int tipo = lerInt(sc);

        UsuarioModel user;

        try {
            user = new UsuarioModel(
                    nome,
                    TipoUsuarioEnum.fromCodigo(tipo)
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido.");
            sc.close();
            return;
        }

        controller.menu(user, sc);

        sc.close();
    }

    private static int lerInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Número inválido.");
            }
        }
    }
}