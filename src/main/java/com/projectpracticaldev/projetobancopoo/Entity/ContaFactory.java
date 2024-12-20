package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaFactory {

  public static Conta criarContaBancaria(
      Integer numero, Integer agencia, String cliente, Double saldo) {
    ContaBancaria conta = new ContaBancaria(numero, agencia, cliente, 0.0, true, new ArrayList<>());
    conta.depositar(saldo);
    return conta;
  }

  public static Conta carregarContaBancaria(
      Integer numero,
      Integer agencia,
      String cliente,
      Double saldo,
      Boolean ativa,
      List<Historico> listaHistorico) {
    return new ContaBancaria(numero, agencia, cliente, saldo, ativa, listaHistorico);
  }

  public static Conta criarContaEspecial(
      Integer numero, Integer agencia, String cliente, Double saldo, Double limite) {
    ContaEspecial conta =
        new ContaEspecial(numero, agencia, cliente, 0.0, true, new ArrayList<>(), limite);
    conta.depositar(saldo);
    return conta;
  }

  public static Conta carregarContaEspecial(
      Integer numero,
      Integer agencia,
      String cliente,
      Double saldo,
      Boolean ativa,
      List<Historico> listaHistorico,
      Double limite) {
    return new ContaEspecial(numero, agencia, cliente, saldo, ativa, listaHistorico, limite);
  }

  public static Conta criarContaPoupanca(
      Integer numero, Integer agencia, String cliente, Double saldo, Double taxaJuros) {
    ContaPoupanca conta =
        new ContaPoupanca(numero, agencia, cliente, 0.0, true, new ArrayList<>(), null, taxaJuros);
    conta.depositar(saldo);
    return conta;
  }

  public static Conta carregarContaPoupanca(
      Integer numero,
      Integer agencia,
      String cliente,
      Double saldo,
      Boolean ativa,
      List<Historico> listaHistorico,
      LocalDateTime dataUltimaMovimentacao,
      Double taxaJuros) {
    return new ContaPoupanca(
        numero, agencia, cliente, saldo, ativa, listaHistorico, dataUltimaMovimentacao, taxaJuros);
  }
}
