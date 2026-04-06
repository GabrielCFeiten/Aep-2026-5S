package com.observaAcao.controllers;

import java.util.Scanner;
import com.observaAcao.models.UsuarioModel;

public class UsuarioController {

    public void menuController(UsuarioModel user, Scanner leitor) {
        user.getTipo().executarMenu(leitor);
    }
}
