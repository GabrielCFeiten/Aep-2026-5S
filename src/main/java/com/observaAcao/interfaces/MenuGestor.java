package com.observaAcao.interfaces;

import com.observaAcao.controllers.LeituraController;
import com.observaAcao.controllers.SolicitacaoController;
import com.observaAcao.models.UsuarioModel;

import java.util.Scanner;

import static com.observaAcao.controllers.LeituraController.lerInt;

public class MenuGestor {

    public static void menuGestor(Scanner leitor, UsuarioModel gestor) {
        LeituraController.limparTela();
        System.out.println("Bem vindo ao menu do Gestor - " + gestor.getNome());

        SolicitacaoController solicitacaoController = new SolicitacaoController();

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n===== MENU GESTOR =====");
            System.out.println("1 - Listar todas as solicitações");
            System.out.println("2 - Buscar solicitação por protocolo");
            System.out.println("3 - Responder / Atualizar status de solicitação");
            System.out.println("4 - Buscar por filtro");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");
            opcao = lerInt(leitor);

            switch (opcao) {
                case 1:
                    solicitacaoController.listarTodasSolicitacoes();
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 2:
                    solicitacaoController.buscarPorProtocolo(leitor);
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 3:
                    solicitacaoController.responderSolicitacao(leitor, gestor);
                    break;

                case 4:
                    solicitacaoController.listarFiltrados(leitor);
                    break;

                case 5:
                    System.out.println("Saindo do menu do Gestor...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}