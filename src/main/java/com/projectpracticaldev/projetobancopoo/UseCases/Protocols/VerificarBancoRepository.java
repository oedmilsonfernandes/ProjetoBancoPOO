package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import com.projectpracticaldev.projetobancopoo.Entity.Resultado;

public interface VerificarBancoRepository {
    public Resultado<Boolean> verSeBancoExiste(Integer agencia);
}
