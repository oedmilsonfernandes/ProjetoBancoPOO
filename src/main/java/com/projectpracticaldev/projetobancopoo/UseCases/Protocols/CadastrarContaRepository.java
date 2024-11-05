package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import com.projectpracticaldev.projetobancopoo.Entity.Conta;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public interface CadastrarContaRepository {
    public Resultado<Boolean> salvar(Conta conta);
}
