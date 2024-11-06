package com.projectpracticaldev.projetobancopoo.UseCases.Protocols;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import java.util.List;

public interface BuscarBancosRepository {
  public Resultado<List<Banco>> buscarBancos();
}
