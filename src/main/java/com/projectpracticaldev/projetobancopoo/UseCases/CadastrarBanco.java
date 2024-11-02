package com.projectpracticaldev.projetobancopoo.UseCases;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.utils.Resultado;

public class CadastrarBanco {

    private CadastrarBancoRepository _cadastrarBancoRepository;

    public CadastrarBanco(CadastrarBancoRepository cadastrarBancoRepository){
        _cadastrarBancoRepository = cadastrarBancoRepository;
    }

    public Resultado<Boolean> execute(String nome, Integer agencia){
        if(nome.length() < 5){
            return new Resultado<Boolean>(false, "O nome deve conter pelo menos 5 caracteres!");
        }

        Resultado<Boolean> bancoJaExiste = _cadastrarBancoRepository.verSeBancoJaExiste(nome);
        if(bancoJaExiste.retorno()){
            return new Resultado<Boolean>(false, "Esse nome de banco já existe!");
        }

        if(agencia < 0){
            return new Resultado<Boolean>(false, "O Numero da agencia é negativo!");
        }

        Banco banco = Banco.criar(nome, agencia);

        Resultado<Boolean> bancoSalvo = _cadastrarBancoRepository.salvar(banco);
        if(!bancoSalvo.retorno()){
            return new Resultado<Boolean>(false, "O banco não pode ser salvo!");
        }

        return new Resultado<Boolean>(true, null);
    }
}
