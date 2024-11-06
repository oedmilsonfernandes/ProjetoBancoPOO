package com.projectpracticaldev.projetobancopoo.UseCases;

import com.projectpracticaldev.projetobancopoo.DTO.BancoDTO;
import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancosRepository;
import java.util.ArrayList;
import java.util.List;

public class BuscarBancos {
  private BuscarBancosRepository _buscarBancosRepository;

  public BuscarBancos(BuscarBancosRepository buscarBancosRepository) {
    _buscarBancosRepository = buscarBancosRepository;
  }

  public Resultado<List<BancoDTO>> execute() {
    Resultado<List<Banco>> buscarBancos = this._buscarBancosRepository.buscarBancos();

    List<BancoDTO> bancos = new ArrayList<>();
    for (Banco b : buscarBancos.pegarValor().get()) {
      bancos.add(new BancoDTO(b.getNome(), b.getAgencia()));
    }

    return Resultado.sucesso(bancos);
  }
}
