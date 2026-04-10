package com.observaAcao.services;

import com.observaAcao.configuracaoDB.SolicitacaoCompletaDTO;
import com.observaAcao.configuracaoDB.SolicitacaoResumoDTO;
import com.observaAcao.enums.CategoriaEnum;
import com.observaAcao.enums.PrioridadeEnum;
import com.observaAcao.enums.StatusEnum;
import com.observaAcao.enums.TipoUsuarioEnum;
import com.observaAcao.models.HistoricoModel;
import com.observaAcao.models.SolicitacaoModel;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.repositories.HistoricoRepository;
import com.observaAcao.repositories.SolicitacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoService {

    private final SolicitacaoRepository banco = new SolicitacaoRepository();
    private final HistoricoRepository historicoRepo = new HistoricoRepository();

    public SolicitacaoModel criar(CategoriaEnum categoria,
                                  String descricao,
                                  String bairro,
                                  String endereco,
                                  UsuarioModel usuario) {

        if (usuario.getTipo() == TipoUsuarioEnum.ANONIMO &&
                !categoria.isPermiteAnonimo()) {

            throw new RuntimeException("Usuário anônimo só pode realizar denúncias");
        }

        if (descricao == null || descricao.trim().length() < 10) {
            throw new RuntimeException("Descrição deve ter no mínimo 10 caracteres");
        }

        SolicitacaoModel newSolicitacao = new SolicitacaoModel(
                categoria,
                descricao,
                bairro,
                endereco,
                usuario.getId()
        );

        SolicitacaoModel s = banco.salvar(newSolicitacao);
        s.setUsuarioId(usuario.getId());

        historicoRepo.salvar(
                new HistoricoModel(
                        s.getProtocolo(),
                        StatusEnum.ABERTO,
                        usuario.getId(),
                        "Solicitação criada"
                )
        );

        return s;
    }

    public void atualizarStatus(int protocolo,
                                StatusEnum novoStatus,
                                UsuarioModel gestor,
                                String justificativa) {

        banco.atualizarStatus(protocolo, novoStatus);

        historicoRepo.salvar(
                new HistoricoModel(
                        protocolo,
                        novoStatus,
                        gestor.getId(),
                        justificativa
                )
        );
    }

    public SolicitacaoCompletaDTO buscarPorProtocolo(int protocolo) {

        SolicitacaoModel s = banco.buscarPorId(protocolo);

        if (s == null) {
            throw new RuntimeException("Solicitação não encontrada");
        }

        List<HistoricoModel> historico =
                historicoRepo.listarPorProtocolo(protocolo);

        return new SolicitacaoCompletaDTO(s, historico);
    }

    public List<SolicitacaoResumoDTO> listarPorUsuario(int usuarioId) {

        List<SolicitacaoModel> solicitacoes =
                banco.listarPorUsuario(usuarioId);

        List<SolicitacaoResumoDTO> resultado = new ArrayList<>();

        for (SolicitacaoModel s : solicitacoes) {

            HistoricoModel ultimo =
                    historicoRepo.buscarUltimoPorProtocolo(s.getProtocolo());

            resultado.add(new SolicitacaoResumoDTO(s, ultimo));
        }

        return resultado;
    }

    public List<SolicitacaoCompletaDTO> listarTodas() {

        List<SolicitacaoModel> solicitacoes = banco.listarTodas();

        List<SolicitacaoCompletaDTO> resultado = new ArrayList<>();

        for (SolicitacaoModel s : solicitacoes) {

            List<HistoricoModel> historico =
                    historicoRepo.listarPorProtocolo(s.getProtocolo());

            resultado.add(new SolicitacaoCompletaDTO(s, historico));
        }

        return resultado;
    }

    public List<SolicitacaoCompletaDTO> listarPorPrioridade(PrioridadeEnum prioridade) {

        List<SolicitacaoModel> solicitacoes =
                banco.listarPorPrioridade(prioridade);

        return montarCompleto(solicitacoes);
    }

    public List<SolicitacaoCompletaDTO> listarPorBairro(String bairro) {

        List<SolicitacaoModel> solicitacoes =
                banco.listarPorBairro(bairro);

        return montarCompleto(solicitacoes);
    }

    public List<SolicitacaoCompletaDTO> listarPorCategoria(CategoriaEnum categoria) {

        List<SolicitacaoModel> solicitacoes =
                banco.listarPorCategoria(categoria);

        return montarCompleto(solicitacoes);
    }

    private List<SolicitacaoCompletaDTO> montarCompleto(List<SolicitacaoModel> lista) {

        List<SolicitacaoCompletaDTO> resultado = new ArrayList<>();

        for (SolicitacaoModel s : lista) {

            List<HistoricoModel> historico =
                    historicoRepo.listarPorProtocolo(s.getProtocolo());

            resultado.add(new SolicitacaoCompletaDTO(s, historico));
        }

        return resultado;
    }
}