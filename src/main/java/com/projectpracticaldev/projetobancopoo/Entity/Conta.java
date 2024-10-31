package com.projectpracticaldev.projetobancopoo.Entity;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public interface Conta {
    public SimpleEntry<Boolean, String> depositar(Double valor);
    public SimpleEntry<Boolean, String> sacar(Double valor);
    public Double exibirSaldo();
    public List<Historico> exibirExtrato();
    public SimpleEntry<Boolean, String> transferir(Double valor, ContaBancaria destino);
    public SimpleEntry<Boolean, String> receberTransferencia(Double valor, DadosBancarios remetente);
    public SimpleEntry<Boolean, String> bloquearConta();
    public SimpleEntry<Boolean, String> desbloquearConta();
}
