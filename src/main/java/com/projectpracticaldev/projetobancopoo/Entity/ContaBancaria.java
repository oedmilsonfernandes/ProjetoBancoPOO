/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
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
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor do deposito é negativo!");
        }
        this.saldo += valor;
        this.listaHistorico.add(new Historico("Deposito", LocalDateTime.now(), valor));
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> sacar(Double valor) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor do saque é negativo!");
        }
        
        if( this.saldo - valor < 0){
            return new SimpleEntry<>(false, "O valor do saque é inviavel!");
        }
        
        this.saldo -= valor;
        this.listaHistorico.add(new Historico("Saque", LocalDateTime.now(), valor));
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
    public SimpleEntry<Boolean, String> transferir(Double valor, ContaBancaria destino) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor da transferencia é negativo!");
        }
        
        if( this.saldo - valor < 0){
            return new SimpleEntry<>(false, "A conta não tem saldo para essa transferencia!");
        }
        
        this.saldo -= valor;
        Historico h = new Historico(
            String.format("Transferencia para {numero: %d, agencia: %d, cliente: %s}",
                destino.getNumero(), destino.getAgencia(), destino.getCliente()),
            LocalDateTime.now(), valor);
        this.listaHistorico.add(h);
        
        DadosBancarios remetente = new DadosBancarios(numero, agencia, cliente);
        SimpleEntry<Boolean, String> transferido = destino.receberTransferencia(valor, remetente);

        if(!transferido.getKey()){
            this.saldo += valor;
            this.listaHistorico.remove(h);
            return transferido;
        }

        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> receberTransferencia(Double valor, DadosBancarios remetente) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor da transferencia é negativo!");
        }

        this.saldo += valor;
        this.listaHistorico.add(new Historico(
            String.format("Transferencia de {numero: %d, agencia: %d, cliente: %s}",
                remetente.numero(), remetente.agencia(), remetente.cliente()),
            LocalDateTime.now(), valor));
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