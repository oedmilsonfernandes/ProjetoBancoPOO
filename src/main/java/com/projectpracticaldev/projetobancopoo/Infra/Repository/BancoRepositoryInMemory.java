package com.projectpracticaldev.projetobancopoo.Infra.Repository;

import java.util.List;
import java.util.ArrayList;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancoRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancosRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.VerificarBancoRepository;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public class BancoRepositoryInMemory implements 
        CadastrarBancoRepository,
        BuscarBancoRepository,
        BuscarBancosRepository,
        VerificarBancoRepository {

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

    @Override
    public Resultado<Banco> buscarBanco(String nome) {
        for(Banco b : bancos){
            if(b.getNome() == nome){
                return Resultado.sucesso(b);
            }
        }
        return Resultado.falhou("Banco não encontrado!");
    }

    @Override
    public Resultado<List<Banco>> buscarBancos() {
        return Resultado.sucesso(bancos);
    }

    @Override
    public Resultado<Boolean> verSeBancoExiste(Integer agencia) {
        for(Banco b : bancos){
            if(b.getAgencia() == agencia){
                return Resultado.sucesso(true);
            }
        }
        return Resultado.falhou("Essa agencia de banco não existe!");
    }

}
