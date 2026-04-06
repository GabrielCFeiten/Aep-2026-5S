package com.observaAcao.controllers;

import com.observaAcao.services.SolicitacaoService;

import java.util.Scanner;

public class SolicitacaoController {

    SolicitacaoService service = new SolicitacaoService();

    public void criarSolicitacao(Scanner leitor){

        while (true) {
            try {
                System.out.println("Digite a categoria:");
                String categoria = leitor.nextLine();

                System.out.println("Digite a descricao:");
                String descricao = leitor.nextLine();

                System.out.println("Digite a localizacao:");
                String localizacao = leitor.nextLine();

                service.criar(categoria, descricao, localizacao);

                System.out.println("Solicitação criada com sucesso!");
                break;

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
