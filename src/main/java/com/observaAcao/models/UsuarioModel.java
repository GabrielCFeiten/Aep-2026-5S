package com.observaAcao.models;

import com.observaAcao.enums.TipoUsuarioEnum;

public class UsuarioModel {

    private Integer id;
    private String nome;
    private TipoUsuarioEnum tipo;

    public UsuarioModel(String nome, TipoUsuarioEnum tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public TipoUsuarioEnum getTipo() { return tipo; }

    public void setId(Integer id) { this.id = id; }
}