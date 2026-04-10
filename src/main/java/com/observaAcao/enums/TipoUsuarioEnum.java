package com.observaAcao.enums;

import java.util.Scanner;
import com.observaAcao.interfaces.MenuAnonimo;
import com.observaAcao.interfaces.MenuGestor;
import com.observaAcao.interfaces.MenuUsuario;
import com.observaAcao.models.UsuarioModel;

public enum TipoUsuarioEnum {

    CIDADAO(1) {
        @Override
        public void executarMenu(Scanner sc, UsuarioModel usuario) {
            MenuUsuario.menuUsuario(sc, usuario);
        }
    },

    GESTOR(2) {
        @Override
        public void executarMenu(Scanner sc, UsuarioModel usuario) {
            MenuGestor.menuGestor(sc, usuario);
        }
    },

    ANONIMO(3) {
        @Override
        public void executarMenu(Scanner sc, UsuarioModel usuario) {
            MenuAnonimo.menuAnonimo(sc, usuario);
        }
    };

    private int codigo;

    TipoUsuarioEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoUsuarioEnum fromCodigo(int codigo) {
        for (TipoUsuarioEnum t : values()) {
            if (t.codigo == codigo) return t;
        }
        throw new IllegalArgumentException("Código inválido");
    }

    public abstract void executarMenu(Scanner sc, UsuarioModel usuario);
}