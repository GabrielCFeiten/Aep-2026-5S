package com.observaAcao.interfaces;

import com.observaAcao.controllers.LeituraController;
import com.observaAcao.controllers.SolicitacaoController;
import com.observaAcao.models.UsuarioModel;

import java.util.Scanner;

import static com.observaAcao.constants.DadosContato.*;
import static com.observaAcao.controllers.LeituraController.lerInt;

public class MenuUsuario {

    public static void menuUsuario(Scanner leitor, UsuarioModel usuario) {
        LeituraController.limparTela();
        System.out.println("Bem vindo ao menu principal do usuario");
        int opcao = 0;
        SolicitacaoController solicitacaoController = new SolicitacaoController();
        while (opcao != 5){

            System.out.println("Menu Principal (Escolha uma das opções)");
            System.out.println("1- Cadastrar solicitação");
            System.out.println("2- Exibir suas solicitações");
            System.out.println("3- Buscar por protocolo");
            System.out.println("4- Mostar dados de contato");
            System.out.println("5- Sair");
            opcao = lerInt(leitor);

            switch (opcao) {
                case 1:
                    solicitacaoController.criarSolicitacao(leitor, usuario);
                    break;
                case 2:
                    solicitacaoController.listarMinhasSolicitacoes(usuario);
                    break;
                case 3:
                    solicitacaoController.buscarPorProtocolo(leitor);
                    break;
                case 4:
                    dadosDeContato();
                    break;
                case 5:
                    System.out.println("Saindo do menu ...");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }

    }

    public static void dadosDeContato(){
        System.out.println("Endereço: " + ENDERECO);
        System.out.println("Horário de atendimento: " + HORARIO_DE_ATENDIMENTO);
        System.out.println("Número de telefone: " + NUMERO_TELEFONE_1);
        System.out.println("Número de telefone 2: " + NUEMRO_TELEFONE_2 + "\n");
    }
}
