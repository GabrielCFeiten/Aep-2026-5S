package com.observaAcao.models;

import com.observaAcao.enums.TipoUsuarioEnum;

import static com.observaAcao.controllers.LeituraController.isCpfValido;

public class UsuarioModel {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private TipoUsuarioEnum tipo;

    public UsuarioModel(Integer id, String nome, String cpf, String telefone, TipoUsuarioEnum tipo) {
        this.id = id;
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

        if (isCpfValido(cpf)){
            this.cpf = cpf;
        }else{
            System.out.println("CPF invalido");
        }
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