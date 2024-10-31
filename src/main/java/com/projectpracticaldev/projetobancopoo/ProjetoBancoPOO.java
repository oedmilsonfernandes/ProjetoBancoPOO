/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.projectpracticaldev.projetobancopoo;

import com.projectpracticaldev.utils.Resultado;
import java.util.List;

import com.projectpracticaldev.projetobancopoo.Entity.Conta;
import com.projectpracticaldev.projetobancopoo.Entity.ContaBancaria;
import com.projectpracticaldev.projetobancopoo.Entity.ContaFactory;
import com.projectpracticaldev.projetobancopoo.Entity.Historico;

/**
 *
 * @author edmilson
 */
public class ProjetoBancoPOO {

    public static void main(String[] args) {
        //################## Demonstração de Conta Bancaria #######################################################
        Conta cb = ContaFactory.criarContaBancaria(1, 200, "Rafael Borges");
        Resultado<Boolean> depositado1 = cb.depositar(500.0);

        if (depositado1.retorno() == false) {
            System.out.println("Deu erro no deposito da conta bancaria: " + depositado1.erro());
            return;
        }
        Resultado<Boolean> saque1 = cb.sacar(50.0);

        if (saque1.retorno() == false) {
            System.out.println("Deu erro no saque da conta bancaria: " + saque1.erro());
            return;
        }
        
        List<Historico> extrato1 = cb.exibirExtrato();
        System.out.println("Mostrando Extrato da Conta Bancaria:");
        for (Historico h : extrato1) {
            System.out.println("Tipo de Movimentação: " + h.tipoMovimentacao() + "\nData da Movimentação: "
                    + h.dataMovimentacao() + "\nValor da Movimentação: " + h.valor());
        }
        System.out.println("\n");

        //################## Demonstração de Conta Especial #######################################################
        Conta ce = ContaFactory.criarContaEspecial(1, 200, "Rafael Borges", 500.0);
        Resultado<Boolean> depositado2 = ce.depositar(500.0);

        if (depositado2.retorno() == false) {
            System.out.println("Deu erro no deposito na Conta Especial: " + depositado2.erro());
            return;
        }
        Resultado<Boolean> saque2 = ce.sacar(600.0);

        if (saque2.retorno() == false) {
            System.out.println("Deu erro no saque na Conta Especial: " + saque2.erro());
            return;
        }

        System.out.println("Saldo da conta bancaria: " + ((ContaBancaria)cb).getSaldo());
        System.out.println("Saldo da conta especial: " + ((ContaBancaria)ce).getSaldo());

        Resultado<Boolean> transferido = cb.transferir(100.0, (ContaBancaria)ce);

        if (transferido.retorno() == false) {
            System.out.println("Deu erro na transferia para Conta Especial: " + transferido.erro());
            return;
        } else {
            System.out.println("Saldo da conta bancaria: " + ((ContaBancaria)cb).getSaldo());
            System.out.println("Saldo da conta especial: " + ((ContaBancaria)ce).getSaldo());
            System.out.println("Transferencia concluida com sucesso!");
        }
        
        List<Historico> extrato2 = ce.exibirExtrato();
        System.out.println("Mostrando Extrato da Conta Especial:");

        for (Historico h : extrato2) {
            System.out.println("Tipo de Movimentação: " + h.tipoMovimentacao() + "\nData da Movimentação: "
                    + h.dataMovimentacao() + "\nValor da Movimentação: " + h.valor());
        }
        System.out.println("\n"); 


        //################## Demonstração de Conta poupança #######################################################
        Conta cp = ContaFactory.criarContaPoupanca(1, 200, "Rafael Borges", 0.005);
        Resultado<Boolean> depositado3 = cp.depositar(500.0);

        if (depositado3.retorno() == false) {
            System.out.println("Deu erro no deposito na Conta Poupança: " + depositado3.erro());
            return;
        }
        Resultado<Boolean> saque3 = cp.sacar(500.0);

        if (saque3.retorno() == false) {
            System.out.println("Deu erro no saque na Conta Poupança: " + saque3.erro());
            return;
        }

        System.out.println("Saldo da conta bancaria: " + ((ContaBancaria)cb).getSaldo());
        System.out.println("Saldo da conta especial: " + ((ContaBancaria)cp).getSaldo());

        Resultado<Boolean> transferido2 = ce.transferir(100.0, (ContaBancaria)cp);

        if (transferido2.retorno() == false) {
            System.out.println("Deu erro na transferia para Conta Poupança: " + transferido2.erro());
            return;
        } else {
            System.out.println("Saldo da conta bancaria: " + ((ContaBancaria)cb).getSaldo());
            System.out.println("Saldo da conta poupanca: " + ((ContaBancaria)cp).getSaldo());
            System.out.println("Transferencia concluida com sucesso!");
        }
        
        List<Historico> extrato3 = cp.exibirExtrato();
        System.out.println("Mostrando Extrato da Conta Poupança:"); 

        for (Historico h : extrato3) {
            System.out.println("Tipo de Movimentação: " + h.tipoMovimentacao() + "\nData da Movimentação: "
                    + h.dataMovimentacao() + "\nValor da Movimentação: " + h.valor());
        }
    }
}
