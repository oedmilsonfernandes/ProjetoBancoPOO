package com.projectpracticaldev.projetobancopoo.DTO;

import java.util.Optional;

public class ContaDTO {
    public enum TipoConta {
        ContaBancaria,
        ContaEspecial,
        ContaPoupanca,
    }

    private TipoConta tipoConta;
    private Integer numero;
    private Integer agencia;
    private String cliente;
    private Double saldo;
    private Double limite;
    private Double taxaJuros;

    public ContaDTO(TipoConta tipoConta, Integer numero, Integer agencia, String cliente, Double saldo, Double limite, Double taxaJuros) {
        this.tipoConta = tipoConta;
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limite = limite;
        this.taxaJuros = taxaJuros;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public String getCliente() {
        return cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Optional<Double> getLimite(){
        return Optional.ofNullable(limite);
    }

    public Optional<Double> getTaxaJuros(){
        return Optional.ofNullable(taxaJuros);
    }    
}
