package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.List;

public interface Conta {
  public Resultado<Boolean> depositar(Double valor);

  public Resultado<Boolean> sacar(Double valor);

  public Double exibirSaldo();

  public List<Historico> exibirExtrato();

  public Resultado<Boolean> transferir(Double valor, ContaBancaria destino);

  public Resultado<Boolean> receberTransferencia(Double valor, DadosBancarios remetente);

  public Resultado<Boolean> bloquearConta();

  public Resultado<Boolean> desbloquearConta();
}
