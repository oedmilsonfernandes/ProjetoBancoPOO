package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private Integer agencia;
    private List<Conta> listaContas;
    public final Double limite = 500.0;
    public final Double taxaJuros = 0.005;

    private Banco(String nome,Integer agencia,  List<Conta> listaContas) {
        this.nome = nome;
        this.agencia = agencia;
        this.listaContas = listaContas;
    }

    public static Banco criar(String nome, Integer agencia){
        return new Banco(nome, agencia, new ArrayList<>());
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getListaContas() {
        return listaContas;
    }

    public void setListaContas(List<Conta> listaContas) {
        this.listaContas = listaContas;
    }
    
    
}
