package com.observaAcao.interfaces;

import com.observaAcao.controllers.LeituraController;
import com.observaAcao.controllers.SolicitacaoController;

import java.util.Scanner;
import static com.observaAcao.controllers.LeituraController.lerInt;

public class MenuUsuario {

    public static void menuUsuario(Scanner leitor) {
        LeituraController.limparTela();
        System.out.println("Bem vindo ao menu principal do usuario");
        int opcao = 0;
        SolicitacaoController solicitacaoController = new SolicitacaoController();
        while (opcao != 4){

            System.out.println("Menu Principal (Escolha uma das opções)");
            System.out.println("1- Cadastrar solicitação");
            System.out.println("2- Exibir suas solicitações");
            System.out.println("3- Buscar por protocolo");
            System.out.println("4- Sair");
            opcao = lerInt(leitor);

            switch (opcao) {
                case 1:
                    solicitacaoController.criarSolicitacao(leitor);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }

    }
}
