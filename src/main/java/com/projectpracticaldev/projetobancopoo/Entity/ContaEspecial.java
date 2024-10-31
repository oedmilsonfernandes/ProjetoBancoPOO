package com.projectpracticaldev.projetobancopoo.Entity;

import java.time.LocalDateTime;
import java.util.List;
import com.projectpracticaldev.utils.Resultado;


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
    public Resultado<Boolean> sacar(Double valor) {
        if(valor < 0){
            return new Resultado<Boolean>(false, "O valor do saque é negativo!");
        }
        
        if(super.getSaldo() + this.limite - valor < 0){
            return new Resultado<Boolean>(false, "O valor do saque é inviavel!");
        }
        
        super.setSaldo(super.getSaldo() - valor);

        List<Historico> lh = super.getListaHistorico();
        lh.add(new Historico("Saque", LocalDateTime.now(), valor));
        super.setListaHistorico(lh);
        
        return new Resultado<Boolean>(true, null);
    }

    @Override
    public Resultado<Boolean> transferir(Double valor, ContaBancaria destino) {
        if(valor < 0){
            return new Resultado<Boolean>(false, "O valor da transferencia é negativo!");
        }
        
        if(super.getSaldo() + this.limite - valor < 0){
            return new Resultado<Boolean>(false, "A conta não tem saldo para essa transferencia!");
        }
        
        super.setSaldo(super.getSaldo() - valor);

        List<Historico> lh = super.getListaHistorico();
        Historico h = new Historico(
            String.format("Transferencia para {numero: %d, agencia: %d, cliente: %s}",
                destino.getNumero(), destino.getAgencia(), destino.getCliente()),
            LocalDateTime.now(), valor);
        lh.add(h);

        DadosBancarios remetente = new DadosBancarios(super.getNumero(), super.getAgencia(), super.getCliente());
        Resultado<Boolean> transferido = destino.receberTransferencia(valor, remetente);

        if(!transferido.retorno()){
            super.setSaldo(super.getSaldo() + valor);
            lh.remove(h);
            return transferido;
        }

        super.setListaHistorico(lh);
        return new Resultado<Boolean>(true, null);
    }

    @Override
    public Resultado<Boolean> bloquearConta() {
        if(super.getSaldo() > 0){
            return new Resultado<Boolean>(false, "Ainda há saldo!");
        }

        if(super.getSaldo() < 0){
            return new Resultado<Boolean>(false, "Ainda há debito!");
        }

        if(super.getAtiva() == false){
            return new Resultado<Boolean>(false, "Conta já está bloqueada");
        }

        super.setAtiva(false);
        return new Resultado<Boolean>(true, null);
    }

}
