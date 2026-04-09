package com.observaAcao.controllers;

import com.observaAcao.configuracaoDB.SolicitacaoCompletaDTO;
import com.observaAcao.configuracaoDB.SolicitacaoResumoDTO;
import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.SolicitacaoService;

import java.util.List;
import java.util.Scanner;

public class SolicitacaoController {

    private final SolicitacaoService service = new SolicitacaoService();

    public void criarSolicitacao(Scanner leitor, UsuarioModel usuario) {

        while (true) {
            try {
                LeituraController.limparTela();

                // =========================
                // ESCOLHA DE CATEGORIA
                // =========================
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

                // =========================
                // ENTRADAS
                // =========================
                String descricao = LeituraController.lerStringMin(
                        leitor,
                        "Digite a descrição (mín 10 caracteres):",
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

                // =========================
                // CRIAÇÃO
                // =========================
                SolicitacaoModel s = service.criar(
                        categoria,
                        descricao,
                        bairro,
                        endereco,
                        usuario
                );

                // =========================
                // SAÍDA
                // =========================
                LeituraController.limparTela();

                System.out.println(s);

                System.out.println("\nPressione ENTER para continuar...");
                leitor.nextLine();

                break;

            } catch (RuntimeException e) {

                System.out.println("\nErro: " + e.getMessage());
                System.out.println("Pressione ENTER para tentar novamente...");
                leitor.nextLine();
            }
        }
    }


    public void buscarPorProtocolo(Scanner sc) {

        try {
            System.out.println("Digite o protocolo:");
            int protocolo = LeituraController.lerInt(sc);

            SolicitacaoCompletaDTO dto =
                    service.buscarPorProtocolo(protocolo);

            // 🔹 Dados da solicitação
            System.out.println(dto.getSolicitacao());

            // 🔹 Histórico
            System.out.println("\n--- HISTÓRICO ---");

            for (HistoricoModel h : dto.getHistorico()) {
                System.out.println(
                        h.getData() + " | " +
                                h.getStatus() + " | " +
                                h.getJustificativa()
                );
            }

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarMinhasSolicitacoes(Scanner sc, UsuarioModel usuario) {

        List<SolicitacaoResumoDTO> lista =
                service.listarPorUsuario(usuario.getId());

        for (SolicitacaoResumoDTO dto : lista) {

            System.out.println(dto.getSolicitacao());

            HistoricoModel h = dto.getUltimoHistorico();

            if (h != null) {
                System.out.println("Última atualização:");
                System.out.println(
                        h.getData() + " | " +
                                h.getStatus() + " | " +
                                h.getJustificativa()
                );
            }

            System.out.println("\n====================\n");
        }
    }
}