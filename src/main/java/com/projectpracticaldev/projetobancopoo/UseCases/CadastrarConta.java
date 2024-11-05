package com.projectpracticaldev.projetobancopoo.UseCases;

import com.projectpracticaldev.projetobancopoo.DTO.ContaDTO;
import com.projectpracticaldev.projetobancopoo.Entity.Conta;
import com.projectpracticaldev.projetobancopoo.Entity.ContaFactory;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarContaRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.VerificarBancoRepository;

public class CadastrarConta {
    private VerificarBancoRepository _verificarBancoRepository;
    private CadastrarContaRepository _cadastrarContaRepository;

    public CadastrarConta(
         VerificarBancoRepository verificarBancoRepository,
         CadastrarContaRepository cadastrarContaRepository
    ){
        this._verificarBancoRepository = verificarBancoRepository;
        this._cadastrarContaRepository = cadastrarContaRepository;
    }

    public Resultado<Boolean> execute(ContaDTO contaDTO){
        if(contaDTO.getAgencia() == null){
            return Resultado.falhou("O Banco não foi informado!");
        }

        Resultado<Boolean> bancoEncontrado = _verificarBancoRepository.verSeBancoExiste(contaDTO.getAgencia());

        if(bancoEncontrado.falhou()){
            return Resultado.falhou("O Banco não foi encontrado!");
        }

        if(contaDTO.getTipoConta() == null){
            return Resultado.falhou("O tipo da conta não foi informado!");
        }

        Resultado<Boolean> contaCadastrada = Resultado.sucesso(true);

        switch (contaDTO.getTipoConta()) {
            case ContaBancaria:
                Conta novaContaBancaria = ContaFactory.criarContaBancaria(contaDTO.getNumero(), contaDTO.getAgencia(), contaDTO.getCliente(), contaDTO.getSaldo());
                contaCadastrada = this._cadastrarContaRepository.salvar(novaContaBancaria);
                break;
            case ContaEspecial:
                Conta novaContaEspecial = ContaFactory.criarContaEspecial(contaDTO.getNumero(), contaDTO.getAgencia(), contaDTO.getCliente(), contaDTO.getSaldo(), contaDTO.getLimite().get());
                contaCadastrada = this._cadastrarContaRepository.salvar(novaContaEspecial);
                break;
            case ContaPoupanca:
                Conta novaContaPoupanca = ContaFactory.criarContaPoupanca(contaDTO.getNumero(), contaDTO.getAgencia(), contaDTO.getCliente(), contaDTO.getSaldo(), contaDTO.getTaxaJuros().get());
                contaCadastrada = this._cadastrarContaRepository.salvar(novaContaPoupanca);
                break;
        }

        if(contaCadastrada.falhou()){
            return Resultado.falhou("A conta não foi cadastrada!");
        }

        return Resultado.sucesso(true);
    }
}
