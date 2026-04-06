package com.observaAcao.enums;

import java.util.Scanner;
import com.observaAcao.interfaces.MenuUsuario;

public enum TipoUsuarioEnum {

    CIDADAO(1) {
        @Override
        public void executarMenu(Scanner sc) {
            MenuUsuario.menuUsuario(sc);
        }
    },

    GESTOR(2) {
        @Override
        public void executarMenu(Scanner sc) {
            System.out.println("Menu gestor");
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

    public abstract void executarMenu(Scanner sc);
}