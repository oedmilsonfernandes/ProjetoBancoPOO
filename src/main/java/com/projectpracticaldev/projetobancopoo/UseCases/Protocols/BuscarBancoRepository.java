package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public interface BuscarBancoRepository {
    public Resultado<Banco> buscarBanco(String nome);
}
