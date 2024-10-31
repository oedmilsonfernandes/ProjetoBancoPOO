package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaFactory {

    public static Conta criarContaBancaria(Integer numero, Integer agencia, String cliente) {
        return new ContaBancaria(numero, agencia, cliente, 0.0, true, new ArrayList<>());
    }

    public static Conta carregarContaBancaria(Integer numero, Integer agencia, String cliente, Double saldo,
            Boolean ativa,
            List<Historico> listaHistorico) {
        return new ContaBancaria(numero, agencia, cliente, saldo, ativa, listaHistorico);
    }

    public static Conta criarContaEspecial(Integer numero, Integer agencia, String cliente, Double limite) {
        return new ContaEspecial(numero, agencia, cliente, 0.0, true, new ArrayList<>(), limite);
    }

    public static Conta carregarContaEspecial(Integer numero, Integer agencia, String cliente, Double saldo,
            Boolean ativa,
            List<Historico> listaHistorico, Double limite) {
        return new ContaEspecial(numero, agencia, cliente, saldo, ativa, listaHistorico, limite);
    }

    public static Conta criarContaPoupanca(Integer numero, Integer agencia, String cliente, Double taxaJuros) {
        return new ContaPoupanca(numero, agencia, cliente, 0.0, true, new ArrayList<>(), null,
                taxaJuros);
    }

    public static Conta carregarContaPoupanca(Integer numero, Integer agencia, String cliente, Double saldo,
            Boolean ativa,
            List<Historico> listaHistorico, LocalDateTime dataUltimaMovimentacao, Double taxaJuros) {
        return new ContaPoupanca(numero, agencia, cliente, saldo, ativa, listaHistorico, dataUltimaMovimentacao,
                taxaJuros);
    }
}
