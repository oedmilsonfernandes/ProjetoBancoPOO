package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class ContaPoupanca extends ContaBancaria implements Aplicacao{
    private LocalDateTime dataUltimaMovimentacao;
    private Double taxaJuros;

    public LocalDateTime getDataUltimaMovimentacao() {
        return dataUltimaMovimentacao;
    }

    public void setDataUltimaMovimentacao(LocalDateTime dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupanca(Integer numero, Integer agencia, String cliente, Double saldo, Boolean ativa,
            List<Historico> listaHistorico, LocalDateTime dataUltimaMovimentacao, Double taxaJuros) {
        super(numero, agencia, cliente, saldo, ativa, listaHistorico);
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
        this.taxaJuros = taxaJuros;
    }

    @Override
    public SimpleEntry<Boolean, String> calcularRendimento(LocalDateTime hoje) {
        if(!super.getAtiva()){
            return new SimpleEntry<>(false, "Conta bloqueada!");
        }

        if(!hoje.isAfter(this.dataUltimaMovimentacao)){
            return new SimpleEntry<>(false, "Data para o calculo invalida!");
        }

        // Calculando a diferença em meses
        long difMeses = ChronoUnit.MONTHS.between(this.dataUltimaMovimentacao, hoje);
        
        // Calculando a diferença em dias
        long difDias = ChronoUnit.DAYS.between(this.dataUltimaMovimentacao.plusMonths(difMeses), hoje);

        if(difMeses < 1 || difDias != 0){
            return new SimpleEntry<>(false, "Não virou o mês ainda para fazer o calculo!");
        }

        Double novoSaldo = super.getSaldo() * Math.pow((1 + this.taxaJuros), difMeses);
        super.setSaldo(novoSaldo);
        return new SimpleEntry<>(true, null);
    }


    @Override
    public SimpleEntry<Boolean, String> depositar(Double valor) {
        SimpleEntry<Boolean, String> deposito = super.depositar(valor);

        if(!deposito.getKey()){
            return deposito;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> sacar(Double valor) {
        SimpleEntry<Boolean, String> saque = super.sacar(valor);

        if(!saque.getKey()){
            return saque;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> transferir(Double valor, ContaBancaria destino) {
        SimpleEntry<Boolean, String> transferencia = super.transferir(valor, destino);

        if(!transferencia.getKey()){
            return transferencia;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> receberTransferencia(Double valor, DadosBancarios remetente) {
        SimpleEntry<Boolean, String> transferido = super.receberTransferencia(valor, remetente);

        if(!transferido.getKey()){
            return transferido;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return new SimpleEntry<>(true, null);
    }

}
