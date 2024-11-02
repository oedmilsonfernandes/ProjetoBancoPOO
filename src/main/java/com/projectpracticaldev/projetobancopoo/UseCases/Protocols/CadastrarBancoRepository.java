package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.utils.Resultado;

public interface CadastrarBancoRepository {
    public Resultado<Boolean> verSeBancoJaExiste(String nome);
    public Resultado<Boolean> salvar(Banco banco);
}
