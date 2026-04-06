package com.observaAcao.models;

import com.observaAcao.enums.*;

public class UsuarioModel {

    private Integer id;
    private String nome;
    private TipoUsuarioEnum tipo;

    public UsuarioModel(String nome, TipoUsuarioEnum tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }
}

