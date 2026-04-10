package com.observaAcao.controllers;

import com.observaAcao.configuracaoDB.SolicitacaoCompletaDTO;
import com.observaAcao.configuracaoDB.SolicitacaoResumoDTO;
import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.enums.PrioridadeEnum;
import com.observaAcao.enums.StatusEnum;
import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.SolicitacaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.observaAcao.controllers.LeituraController.lerInt;

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

            //Dados da solicitação
            System.out.println(dto.getSolicitacao());

            //Histórico
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

    public void listarMinhasSolicitacoes(UsuarioModel usuario) {

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

    public void listarFiltrados(Scanner sc) {

        try {
            System.out.println("\n=== FILTRAR SOLICITAÇÕES ===");
            System.out.println("1 - Por prioridade");
            System.out.println("2 - Por bairro");
            System.out.println("3 - Por categoria");

            int op = LeituraController.lerInt(sc);

            List<SolicitacaoCompletaDTO> lista = new ArrayList<>();

            switch (op) {

                case 1 -> {
                    System.out.println("Digite a prioridade (ALTA, MEDIA, BAIXA):");
                    PrioridadeEnum prioridade =
                            PrioridadeEnum.valueOf(sc.nextLine().toUpperCase());

                    lista = service.listarPorPrioridade(prioridade);
                }

                case 2 -> {
                    System.out.println("Digite o bairro:");
                    String bairro = sc.nextLine();

                    lista = service.listarPorBairro(bairro);
                }

                case 3 -> {
                    System.out.println("Escolha a categoria:");

                    CategoriaEnum[] opcoes = CategoriaEnum.values();

                    for (int i = 0; i < opcoes.length; i++) {
                        System.out.println((i + 1) + " - " + opcoes[i].name());
                    }

                    int escolha = LeituraController.lerInt(sc);

                    if (escolha < 1 || escolha > opcoes.length) {
                        throw new RuntimeException("Opção inválida");
                    }

                    CategoriaEnum categoria = opcoes[escolha - 1];

                    lista = service.listarPorCategoria(categoria);
                }

                default -> throw new RuntimeException("Opção inválida");
            }

            exibirLista(lista);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void exibirLista(List<SolicitacaoCompletaDTO> lista) {

        if (lista.isEmpty()) {
            System.out.println("\nNenhuma solicitação encontrada.");
            return;
        }

        for (SolicitacaoCompletaDTO dto : lista) {

            System.out.println(dto.getSolicitacao());

            System.out.println("--- HISTÓRICO ---");

            for (HistoricoModel h : dto.getHistorico()) {
                System.out.println(
                        h.getData() + " | " +
                                h.getStatus() + " | " +
                                h.getJustificativa()
                );
            }

            System.out.println("\n====================\n");
        }
    }

    public void listarTodasSolicitacoes() {
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

    public void responderSolicitacao(Scanner leitor, UsuarioModel gestor) {
        LeituraController.limparTela();
        System.out.println("===== RESPONDER SOLICITAÇÃO =====\n");

        try {
            System.out.println("Digite o número do protocolo:");
            int protocolo = lerInt(leitor);

            SolicitacaoCompletaDTO dto = service.buscarPorProtocolo(protocolo);
            System.out.println("\n--- SOLICITAÇÃO ---");
            System.out.println(dto.getSolicitacao());

            System.out.println("\n--- HISTÓRICO ---");
            for (HistoricoModel h : dto.getHistorico()) {
                System.out.println(h.getData() + " | " + h.getStatus() + " | " + h.getJustificativa());
            }

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