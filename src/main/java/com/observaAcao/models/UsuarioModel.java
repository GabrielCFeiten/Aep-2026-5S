package com.observaAcao.models;

import com.observaAcao.enums.TipoUsuarioEnum;



public class UsuarioModel {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private TipoUsuarioEnum tipo;

    public UsuarioModel(String nome, String cpf, String telefone, TipoUsuarioEnum tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public TipoUsuarioEnum getTipo() { return tipo; }

    public void setId(Integer id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }

}