package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaFactory {

    public Conta criarContaBancaria(Integer numero, Integer agencia, String cliente, Double saldo) {
        return new ContaBancaria(numero, agencia, cliente, saldo, true, new ArrayList<>());
    }

    public Conta carregarContaBancaria(Integer numero, Integer agencia, String cliente, Double saldo, Boolean ativa,
            List<Historico> listaHistorico) {
        return new ContaBancaria(numero, agencia, cliente, saldo, ativa, listaHistorico);
    }

    public Conta criarContaEspecial(Integer numero, Integer agencia, String cliente, Double saldo, Double limite) {
        return new ContaEspecial(numero, agencia, cliente, saldo, true, new ArrayList<>(), limite);
    }

    public Conta carregarContaEspecial(Integer numero, Integer agencia, String cliente, Double saldo, Boolean ativa,
            List<Historico> listaHistorico, Double limite) {
        return new ContaEspecial(numero, agencia, cliente, saldo, ativa, listaHistorico, limite);
    }

    public Conta criarContaPoupanca(Integer numero, Integer agencia, String cliente, Double saldo,
            LocalDateTime dataUltimaMovimentacao, Double taxaJuros) {
        return new ContaPoupanca(numero, agencia, cliente, saldo, true, new ArrayList<>(), dataUltimaMovimentacao,
                taxaJuros);
    }

    public Conta carregarContaPoupanca(Integer numero, Integer agencia, String cliente, Double saldo, Boolean ativa,
            List<Historico> listaHistorico, LocalDateTime dataUltimaMovimentacao, Double taxaJuros) {
        return new ContaPoupanca(numero, agencia, cliente, saldo, ativa, listaHistorico, dataUltimaMovimentacao,
                taxaJuros);
    }
}
