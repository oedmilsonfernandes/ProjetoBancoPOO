/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.projectpracticaldev.projetobancopoo;

import com.projectpracticaldev.projetobancopoo.Entity.Conta;
import com.projectpracticaldev.projetobancopoo.Entity.ContaFactory;
import com.projectpracticaldev.projetobancopoo.Entity.Historico;

/**
 *
 * @author edmilson
 */
public class ProjetoBancoPOO {

    public static void main(String[] args) {
        Conta primeiraCB = ContaFactory.criarContaBancaria(1, 1, "Carinha que mora logo ali");
        Conta primeiraCE = ContaFactory.criarContaEspecial(2, 2, "Nelsinho que corre", 100.0);
        Conta primeiraCP = ContaFactory.criarContaPoupanca(3,3,"Gaiato da Coca Cola",0.005);

        System.out.println("\nMostrando deposito:");

        primeiraCB.depositar(10000.0);
        primeiraCE.depositar(1000.0);
        primeiraCP.depositar(100000.0);

        System.out.println("Mostrar saldo da conta Bancaria: " + primeiraCB.exibirSaldo());
        System.out.println("Mostrar saldo da conta Especial: " + primeiraCE.exibirSaldo());
        System.out.println("Mostrar saldo da conta Poupanca: " + primeiraCP.exibirSaldo());
        
        System.out.println("\nMostrar extrato da conta Bancaria:");

        for(Historico h: primeiraCB.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }
        System.out.println("\nMostrar extrato da conta Especial:");

        for(Historico h: primeiraCE.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }
        System.out.println("\nMostrar extrato da conta Poupanca:");

        for(Historico h: primeiraCP.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }

        System.out.println("\nMostrando saque:");

        primeiraCB.sacar(100.0);
        primeiraCE.sacar(100.0);
        primeiraCP.sacar(1000.0);

        System.out.println("Mostrar saldo da conta Bancaria: " + primeiraCB.exibirSaldo());
        System.out.println("Mostrar saldo da conta Especial: " + primeiraCE.exibirSaldo());
        System.out.println("Mostrar saldo da conta Poupanca: " + primeiraCP.exibirSaldo());
        
        System.out.println("\nMostrar extrato da conta Bancaria:");

        for(Historico h: primeiraCB.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }
        System.out.println("\nMostrar extrato da conta Especial:");

        for(Historico h: primeiraCE.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }
        System.out.println("\nMostrar extrato da conta Poupanca:");

        for(Historico h: primeiraCP.exibirExtrato()){
            System.out.printf("Tipo de Movimentação: %s, Data da Movimentação: %s, Valor da MOvimentação: %.2f\n",
                h.tipoMovimentacao(), h.dataMovimentacao(), h.valor());
        }
        
    }
}
