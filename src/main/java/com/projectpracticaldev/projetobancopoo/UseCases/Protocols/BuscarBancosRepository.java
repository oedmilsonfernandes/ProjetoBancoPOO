package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import java.util.List;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public interface BuscarBancosRepository {
    public Resultado<List<Banco>> buscarBancos();
}
