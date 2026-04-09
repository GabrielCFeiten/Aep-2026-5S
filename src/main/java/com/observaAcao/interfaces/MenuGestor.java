package com.observaAcao.interfaces;

import com.observaAcao.configuracaoDB.SolicitacaoCompletaDTO;
import com.observaAcao.controllers.LeituraController;
import com.observaAcao.controllers.SolicitacaoController;
import com.observaAcao.enums.StatusEnum;
import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.SolicitacaoService;

import java.util.List;
import java.util.Scanner;

import static com.observaAcao.controllers.LeituraController.lerInt;

public class MenuGestor {

    public static void menuGestor(Scanner leitor, UsuarioModel gestor) {
        LeituraController.limparTela();
        System.out.println("Bem vindo ao menu do Gestor - " + gestor.getNome());

        SolicitacaoController solicitacaoController = new SolicitacaoController();
        SolicitacaoService service = new SolicitacaoService();

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n===== MENU GESTOR =====");
            System.out.println("1 - Listar todas as solicitações");
            System.out.println("2 - Buscar solicitação por protocolo");
            System.out.println("3 - Responder / Atualizar status de solicitação");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            opcao = lerInt(leitor);

            switch (opcao) {
                case 1:
                    listarTodasSolicitacoes(service);
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 2:
                    solicitacaoController.buscarPorProtocolo(leitor);
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 3:
                    responderSolicitacao(leitor, gestor, service);
                    break;

                case 4:
                    System.out.println("Saindo do menu do Gestor...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // ============================================================
    // LISTAR TODAS AS SOLICITAÇÕES
    // ============================================================
    private static void listarTodasSolicitacoes(SolicitacaoService service) {
        LeituraController.limparTela();
        System.out.println("===== TODAS AS SOLICITAÇÕES =====\n");

        List<SolicitacaoCompletaDTO> lista = service.listarTodas();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma solicitação cadastrada.");
            return;
        }

        for (SolicitacaoCompletaDTO dto : lista) {
            System.out.println(dto.getSolicitacao());

            List<HistoricoModel> historico = dto.getHistorico();
            if (!historico.isEmpty()) {
                HistoricoModel ultimo = historico.get(historico.size() - 1);
                System.out.println("Último status: " + ultimo.getStatus()
                        + " | " + ultimo.getData()
                        + " | " + ultimo.getJustificativa());
            }

            System.out.println("====================");
        }
    }

    // ============================================================
    // RESPONDER / ATUALIZAR STATUS DE UMA SOLICITAÇÃO
    // ============================================================
    private static void responderSolicitacao(Scanner leitor, UsuarioModel gestor, SolicitacaoService service) {
        LeituraController.limparTela();
        System.out.println("===== RESPONDER SOLICITAÇÃO =====\n");

        try {
            System.out.println("Digite o número do protocolo:");
            int protocolo = lerInt(leitor);

            // Busca e exibe a solicitação para o gestor visualizar antes de responder
            SolicitacaoCompletaDTO dto = service.buscarPorProtocolo(protocolo);
            System.out.println("\n--- SOLICITAÇÃO ---");
            System.out.println(dto.getSolicitacao());

            // Exibir histórico existente
            System.out.println("\n--- HISTÓRICO ---");
            for (HistoricoModel h : dto.getHistorico()) {
                System.out.println(h.getData() + " | " + h.getStatus() + " | " + h.getJustificativa());
            }

            // Escolha do novo status
            StatusEnum[] statusOpcoes = StatusEnum.values();
            String[] nomesStatus = new String[statusOpcoes.length];
            for (int i = 0; i < statusOpcoes.length; i++) {
                nomesStatus[i] = statusOpcoes[i].name();
            }

            int indexStatus = LeituraController.escolherOpcao(
                    leitor,
                    "\nEscolha o novo status para a solicitação:",
                    nomesStatus
            );

            StatusEnum novoStatus = statusOpcoes[indexStatus];

            // Justificativa obrigatória
            String justificativa = LeituraController.lerStringMin(
                    leitor,
                    "Digite a justificativa / resposta (mín. 10 caracteres):",
                    10
            );

            // Atualiza no banco
            service.atualizarStatus(protocolo, novoStatus, gestor, justificativa);

            System.out.println("\nSolicitação atualizada com sucesso!");
            System.out.println("Protocolo: " + protocolo
                    + " | Novo status: " + novoStatus.name());

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        leitor.nextLine();
    }
}