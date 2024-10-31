package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.List;

public class Banco {
    private String nome;
    private List<Conta> listaContas;
    public final Double limite = 500.0;
    public final Double taxaJuros = 0.005;
    
    public Banco(String nome, List<Conta> listaContas) {
        this.nome = nome;
        this.listaContas = listaContas;
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
