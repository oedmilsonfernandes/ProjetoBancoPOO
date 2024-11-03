package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


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
    public Resultado<Boolean> calcularRendimento(LocalDateTime hoje) {
        if(!super.getAtiva()){
            return Resultado.falhou("Conta bloqueada!");
        }

        if(!hoje.isAfter(this.dataUltimaMovimentacao)){
            return Resultado.falhou("Data para o calculo invalida!");
        }

        // Calculando a diferença em meses
        long difMeses = ChronoUnit.MONTHS.between(this.dataUltimaMovimentacao, hoje);
        
        // Calculando a diferença em dias
        long difDias = ChronoUnit.DAYS.between(this.dataUltimaMovimentacao.plusMonths(difMeses), hoje);

        if(difMeses < 1 || difDias != 0){
            return Resultado.falhou("Não virou o mês ainda para fazer o calculo!");
        }

        Double novoSaldo = super.getSaldo() * Math.pow((1 + this.taxaJuros), difMeses);
        super.setSaldo(novoSaldo);
        return Resultado.sucesso(true);
    }


    @Override
    public Resultado<Boolean> depositar(Double valor) {
        Resultado<Boolean> deposito = super.depositar(valor);

        if(!deposito.sucesso()){
            return deposito;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return Resultado.sucesso(true);
    }

    @Override
    public Resultado<Boolean> sacar(Double valor) {
        Resultado<Boolean> saque = super.sacar(valor);

        if(!saque.sucesso()){
            return saque;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return Resultado.sucesso(true);
    }

    @Override
    public Resultado<Boolean> transferir(Double valor, ContaBancaria destino) {
        Resultado<Boolean> transferencia = super.transferir(valor, destino);

        if(!transferencia.sucesso()){
            return transferencia;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return Resultado.sucesso(true);
    }

    @Override
    public Resultado<Boolean> receberTransferencia(Double valor, DadosBancarios remetente) {
        Resultado<Boolean> transferido = super.receberTransferencia(valor, remetente);

        if(!transferido.sucesso()){
            return transferido;
        }
        this.dataUltimaMovimentacao = LocalDateTime.now();
        return Resultado.sucesso(true);
    }

    @Override
    public Resultado<Boolean> bloquearConta() {
        Resultado<Boolean> bloqueou = super.bloquearConta();

        if(!bloqueou.sucesso()){
            return bloqueou;
        }

        this.dataUltimaMovimentacao = null;
        return Resultado.sucesso(true);
    }
}
