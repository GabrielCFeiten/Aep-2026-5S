package com.observaAcao.controllers;

import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.SolicitacaoService;

import java.util.Scanner;

public class SolicitacaoController {

    private final SolicitacaoService service = new SolicitacaoService();

    public void criarSolicitacao(Scanner leitor, UsuarioModel usuario) {

        while (true) {
            try {
                LeituraController.limparTela();

                CategoriaEnum[] opcoes = CategoriaEnum.values();

                String[] nomes = new String[opcoes.length];
                for (int i = 0; i < opcoes.length; i++) {
                    nomes[i] = opcoes[i].name();
                }

                int index = LeituraController.escolherOpcao(
                        leitor,
                        "Escolha a categoria:",
                        nomes
                );

                CategoriaEnum categoria = opcoes[index];

                String descricao = LeituraController.lerStringMin(
                        leitor,
                        "Digite a descrição:",
                        10
                );

                String endereco = LeituraController.lerString(
                        leitor,
                        "Digite o endereço:"
                );

                String bairro = LeituraController.lerString(
                        leitor,
                        "Digite o bairro:"
                );

                SolicitacaoModel s = service.criar(
                        categoria,
                        descricao,
                        bairro,
                        endereco,
                        usuario
                );

                LeituraController.limparTela();

                System.out.println("=== SOLICITAÇÃO CRIADA ===");
                System.out.println("Protocolo: " + s.getProtocolo());
                System.out.println("Categoria: " + s.getCategoria());
                System.out.println("Descrição: " + s.getDescricao());
                System.out.println("Prioridade: " + s.getPrioridade());
                System.out.println("Prazo: " + s.getPrazo());
                System.out.println("Bairro: " + s.getBairro());
                System.out.println("Endereço: " + s.getEndereco());
                System.out.println("Status: " + s.getStatus());

                System.out.println("\nPressione ENTER...");
                leitor.nextLine();

                break;

            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
                leitor.nextLine();
            }
        }
    }
}