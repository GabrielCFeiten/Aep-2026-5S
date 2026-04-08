package com.observaAcao.controllers;

import java.util.Scanner;

public class LeituraController {

    // =========================
    // LEITURA DE INTEIRO
    // =========================
    public static int lerInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Valor inválido, digite um número inteiro.");
            }
        }
    }

    // =========================
    // LEITURA DE STRING (não vazia)
    // =========================
    public static String lerString(Scanner sc, String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Campo não pode ser vazio.");
        }
    }

    // =========================
    // LEITURA COM TAMANHO MÍNIMO
    // =========================
    public static String lerStringMin(Scanner sc, String mensagem, int min) {
        while (true) {
            System.out.println(mensagem);
            String input = sc.nextLine().trim();

            if (input.length() >= min) {
                return input;
            }

            System.out.println("Digite pelo menos " + min + " caracteres.");
        }
    }

    // =========================
    // MENU DE OPÇÕES (ENUM)
    // =========================
    public static int escolherOpcao(Scanner sc, String titulo, String[] opcoes) {

        while (true) {
            System.out.println("\n" + titulo);

            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + " - " + opcoes[i]);
            }

            System.out.print("Escolha: ");
            int escolha = lerInt(sc);

            if (escolha >= 1 && escolha <= opcoes.length) {
                return escolha - 1;
            }

            System.out.println("Opção inválida.");
        }
    }

    // =========================
    // LIMPAR TELA (SEGURO)
    // =========================
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean isCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                d1 += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
                d2 += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            d1 = (d1 * 10) % 11 % 10;
            d2 = ((d2 + (d1 * 2)) * 10) % 11 % 10;
            return d1 == Character.getNumericValue(cpf.charAt(9)) && d2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) { return false; }
    }
}