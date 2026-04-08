package com.observaAcao.controllers;

import java.util.Scanner;

import com.observaAcao.enums.TipoUsuarioEnum;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.UsuarioService;

public class UsuarioController {

    private final UsuarioService service = new UsuarioService();

    public void menuController(UsuarioModel user, Scanner leitor) {
        user.getTipo().executarMenu(leitor);
    }

    public UsuarioModel criarUsuario(Scanner sc) {

        System.out.println("Escolha o tipo de usuário:");

        for (TipoUsuarioEnum t : TipoUsuarioEnum.values()) {
            System.out.println(t.getCodigo() + " - " + t.name());
        }

        int codigo = LeituraController.lerInt(sc);

        TipoUsuarioEnum tipo = TipoUsuarioEnum.fromCodigo(codigo);

        String nome = LeituraController.lerString(sc, "Digite o nome:");

        return service.criar(nome, tipo);
    }
}
