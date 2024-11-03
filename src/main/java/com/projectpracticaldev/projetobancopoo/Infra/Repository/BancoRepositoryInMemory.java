package com.projectpracticaldev.projetobancopoo.Infra.Repository;

import java.util.List;
import java.util.ArrayList;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public class BancoRepositoryInMemory implements CadastrarBancoRepository {

    private List<Banco> bancos;

    public BancoRepositoryInMemory(){
        this.bancos = new ArrayList<>();
    }

    @Override
    public Resultado<Boolean> verSeBancoJaExiste(String nome) {
        for(Banco b : bancos){
            if(b.getNome() == nome){
                return Resultado.sucesso(true);
            }
        }
        return Resultado.falhou("Esse nome de banco não existe!");
    }

    @Override
    public Resultado<Boolean> salvar(Banco banco) {
        bancos.add(banco);
        return Resultado.sucesso(true);
    }

}
