package com.projectpracticaldev.projetobancopoo.Infra.Repository;

import java.util.ArrayList;
import java.util.List;

import com.projectpracticaldev.projetobancopoo.Entity.Conta;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarContaRepository;

public class ContaRepositoryInMemory implements CadastrarContaRepository{
    private List<Conta> contas;

    public ContaRepositoryInMemory(){
        this.contas = new ArrayList<>();
    }

    @Override
    public Resultado<Boolean> salvar(Conta conta) {
        contas.add(conta);
        return Resultado.sucesso(true);
    }

}
