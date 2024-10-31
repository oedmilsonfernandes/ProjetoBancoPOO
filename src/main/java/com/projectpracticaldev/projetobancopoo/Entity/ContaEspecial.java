package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;


public class ContaEspecial extends ContaBancaria{
    private Double limite;

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public ContaEspecial(Integer numero, Integer agencia, String cliente, Double saldo, Boolean ativa,
            List<Historico> listaHistorico, Double limite) {
        super(numero, agencia, cliente, saldo, ativa, listaHistorico);
        this.limite = limite;
    }

    @Override
    public SimpleEntry<Boolean, String> sacar(Double valor) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor do saque é negativo!");
        }
        
        if(super.getSaldo() + this.limite - valor < 0){
            return new SimpleEntry<>(true, "O valor do saque é inviavel!");
        }
        
        super.setSaldo(super.getSaldo() - valor);

        List<Historico> lh = super.getListaHistorico();
        lh.add(new Historico("Saque", LocalDateTime.now(), valor));
        super.setListaHistorico(lh);
        
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> transferir(Double valor) {
        if(valor < 0){
            return new SimpleEntry<>(false, "O valor da transferencia é negativo!");
        }
        
        if(super.getSaldo() + this.limite - valor < 0){
            return new SimpleEntry<>(true, "O valor da transferencia é inviavel!");
        }
        
        super.setSaldo(super.getSaldo() - valor);

        List<Historico> lh = super.getListaHistorico();
        lh.add(new Historico("Transferencia", LocalDateTime.now(), valor));
        super.setListaHistorico(lh);
        
        return new SimpleEntry<>(true, null);
    }

    @Override
    public SimpleEntry<Boolean, String> bloquearConta() {
        if(super.getSaldo() > 0){
            return new SimpleEntry<>(false, "Ainda há saldo!");
        }

        if(super.getSaldo() < 0){
            return new SimpleEntry<>(false, "Ainda há debito!");
        }

        if(super.getAtiva() == false){
            return new SimpleEntry<>(false, "Conta já está bloqueada");
        }

        super.setAtiva(false);
        return new SimpleEntry<>(true, null);
    }

}
