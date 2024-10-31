/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.Date;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author edmilson
 */
public class ContaBancaria implements Conta {
    private Integer numero;
    private Integer agencia;
    private String cliente;
    private Double saldo;
    private Boolean ativa;
    private List<Historico> listaHistorico;
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public List<Historico> getListaHistorico() {
        return listaHistorico;
    }

    public void setListaHistorico(List<Historico> listaHistorico) {
        this.listaHistorico = listaHistorico;
    }

    public ContaBancaria(Integer numero, Integer agencia,String cliente, Double saldo, Boolean ativa, List<Historico> listaHistorico){
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.ativa = ativa;
        this.listaHistorico = listaHistorico;
    }

    @Override
    public SimpleEntry<Boolean, String> depositar(Double valor) {
        if(valor >= 0){
            this.saldo += valor;
            return new SimpleEntry<>(true, null);
        }
        this.listaHistorico.add(new Historico("Deposito",new Date(), valor));
        return new SimpleEntry<>(false, "O valor do deposito é negativo!");
    }

    @Override
    public SimpleEntry<Boolean, String> sacar(Double valor) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor do saque é negativo!");
        }
        
        if( this.saldo - valor < 0){
            return new SimpleEntry<>(true, "O valor do saque é inviavel!");
        }
        
        this.saldo -= valor;
        this.listaHistorico.add(new Historico("Saque",new Date(), valor));
        return new SimpleEntry<>(true, null);
    }

    @Override
    public Double exibirSaldo() {
        return this.saldo;
    }

    @Override
    public List<Historico> exibirExtrato() {
        return this.listaHistorico;
    }

    @Override
    public SimpleEntry<Boolean, String> transferir(Double valor) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor da transferencia é negativo!");
        }
        
        if( this.saldo - valor < 0){
            return new SimpleEntry<>(true, "O valor da transferencia é inviavel!");
        }
        
        this.saldo -= valor;
        this.listaHistorico.add(new Historico("Transferencia",new Date(), valor));
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> bloquearConta() {
        if(this.saldo > 0){
            return new SimpleEntry<>(false, "Ainda há saldo!");
        }

        if(this.ativa == false){
            return new SimpleEntry<>(false, "Conta já está bloqueada");
        }

        this.ativa = false;
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> desbloquearConta() {
        if(this.ativa == true){
            return new SimpleEntry<>(false, "Conta já está desbloqueada");
        }

        this.ativa = true;
        return new SimpleEntry<>(true, null);
    }
}