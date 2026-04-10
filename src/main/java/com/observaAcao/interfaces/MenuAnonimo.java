package com.observaAcao.interfaces;

import com.observaAcao.configuracaoDB.SolicitacaoCompletaDTO;
import com.observaAcao.controllers.LeituraController;
import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.SolicitacaoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.observaAcao.constants.DadosContato.*;
import static com.observaAcao.controllers.LeituraController.lerInt;

public class MenuAnonimo {

    public static void menuAnonimo(Scanner leitor, UsuarioModel usuario) {
        LeituraController.limparTela();
        System.out.println("Bem vindo ao sistema ObservaAção - Acesso Anônimo");
        System.out.println("ATENÇÃO: O acesso anônimo permite apenas o registro de DENÚNCIAS.\n");

        SolicitacaoService service = new SolicitacaoService();

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n===== MENU ANÔNIMO =====");
            System.out.println("1 - Registrar denúncia");
            System.out.println("2 - Acompanhar denúncia por protocolo");
            System.out.println("3 - Dados de contato");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            opcao = lerInt(leitor);

            switch (opcao) {
                case 1:
                    registrarDenuncia(leitor, usuario, service);
                    break;

                case 2:
                    acompanharPorProtocolo(leitor, service);
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 3:
                    dadosDeContato();
                    System.out.println("\nPressione ENTER para continuar...");
                    leitor.nextLine();
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // ============================================================
    // REGISTRAR DENÚNCIA (apenas categorias com permiteAnonimo = true)
    // ============================================================
    private static void registrarDenuncia(Scanner leitor, UsuarioModel usuario, SolicitacaoService service) {
        while (true) {
            try {
                LeituraController.limparTela();

                // Filtra apenas as categorias que permitem anônimo (denúncias)
                CategoriaEnum[] todasCategorias = CategoriaEnum.values();
                List<CategoriaEnum> categoriasDenuncia = new ArrayList<>();

                for (CategoriaEnum c : todasCategorias) {
                    if (c.isPermiteAnonimo()) {
                        categoriasDenuncia.add(c);
                    }
                }

                // Monta o array de nomes apenas das denúncias
                String[] nomes = new String[categoriasDenuncia.size()];
                for (int i = 0; i < categoriasDenuncia.size(); i++) {
                    nomes[i] = categoriasDenuncia.get(i).name();
                }

                int index = LeituraController.escolherOpcao(
                        leitor,
                        "Escolha o tipo de denúncia:",
                        nomes
                );

                CategoriaEnum categoria = categoriasDenuncia.get(index);

                // Entradas
                String descricao = LeituraController.lerStringMin(
                        leitor,
                        "Descreva a denúncia (mín. 10 caracteres):",
                        10
                );

                String endereco = LeituraController.lerString(
                        leitor,
                        "Digite o endereço do local:"
                );

                String bairro = LeituraController.lerString(
                        leitor,
                        "Digite o bairro:"
                );

                // Criação da solicitação
                SolicitacaoModel s = service.criar(categoria, descricao, bairro, endereco, usuario);

                LeituraController.limparTela();
                System.out.println("Denúncia registrada com sucesso!");
                System.out.println(s);
                System.out.println("\nGuarde o número do protocolo para acompanhar sua denúncia.");
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

    // ============================================================
    // ACOMPANHAR POR PROTOCOLO
    // ============================================================
    private static void acompanharPorProtocolo(Scanner leitor, SolicitacaoService service) {
        LeituraController.limparTela();

        try {
            System.out.println("Digite o número do protocolo:");
            int protocolo = lerInt(leitor);

            SolicitacaoCompletaDTO dto = service.buscarPorProtocolo(protocolo);

            System.out.println("\n--- DADOS DA DENÚNCIA ---");
            System.out.println(dto.getSolicitacao());

            System.out.println("\n--- HISTÓRICO DE ATUALIZAÇÕES ---");
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

    // ============================================================
    // DADOS DE CONTATO
    // ============================================================
    public static void dadosDeContato() {
        System.out.println("\n--- DADOS DE CONTATO ---");
        System.out.println("Endereço: " + ENDERECO);
        System.out.println("Horário de atendimento: " + HORARIO_DE_ATENDIMENTO);
        System.out.println("Número de telefone: " + NUMERO_TELEFONE_1);
        System.out.println("Número de telefone 2: " + NUEMRO_TELEFONE_2);
    }
}