package com.observaAcao.controllers;

import java.util.Scanner;

import com.observaAcao.enums.TipoUsuarioEnum;
import com.observaAcao.models.UsuarioModel;
import com.observaAcao.services.UsuarioService;


public class UsuarioController {

    private final UsuarioService service = new UsuarioService();

    public UsuarioModel criarUsuario(Scanner sc) {

        System.out.println("Escolha o tipo:");

        for (TipoUsuarioEnum t : TipoUsuarioEnum.values()) {
            System.out.println(t.getCodigo() + " - " + t.name());
        }

        TipoUsuarioEnum tipo = TipoUsuarioEnum.fromCodigo(LeituraController.lerInt(sc));

        String nome;
        String cpf = null;
        String telefone;

        if (tipo == TipoUsuarioEnum.ANONIMO) {

            System.out.println("Nome (opcional):");
            nome = sc.nextLine();

            System.out.println("Telefone (opcional):");
            telefone = sc.nextLine();

        } else {

            nome = LeituraController.lerString(sc, "Digite o nome:");

            cpf = LeituraController.lerString(sc, "Digite o CPF:");

            telefone = LeituraController.lerString(sc, "Digite o telefone:");
        }

        return service.criar(nome, tipo, cpf, telefone);
    }


}
