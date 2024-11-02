package com.projectpracticaldev.projetobancopoo.Infra.Repository;

import java.util.List;
import java.util.ArrayList;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.utils.Resultado;

public class BancoRepositoryInMemory implements CadastrarBancoRepository {

    private List<Banco> bancos;

    public BancoRepositoryInMemory(){
        this.bancos = new ArrayList<>();
    }

    @Override
    public Resultado<Boolean> verSeBancoJaExiste(String nome) {
        for(Banco b : bancos){
            if(b.getNome() == nome){
                return new Resultado<Boolean>(true, "Esse nome de banco já existe!");
            }
        }
        return new Resultado<Boolean>(false,null);
    }

    @Override
    public Resultado<Boolean> salvar(Banco banco) {
        bancos.add(banco);
        return new Resultado<Boolean>(true,null);
    }

}
