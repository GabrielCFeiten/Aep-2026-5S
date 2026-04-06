package com.observaAcao.controllers;

import java.util.Scanner;

public class LeituraController {

    public static int lerInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Valor inválido, digite um número inteiro." +
                                   " Tente " +
                                   "novamente.");
            }
        }
    }

}
