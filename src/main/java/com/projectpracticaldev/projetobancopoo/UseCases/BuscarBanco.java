package com.projectpracticaldev.projetobancopoo.UseCases;

import com.projectpracticaldev.projetobancopoo.DTO.BancoDTO;
import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancoRepository;
import java.util.Optional;

public class BuscarBanco {
  private BuscarBancoRepository _buscarBancoRepository;

  public BuscarBanco(BuscarBancoRepository buscarBancoRepository) {
    _buscarBancoRepository = buscarBancoRepository;
  }

  public Resultado<BancoDTO> execute(String nome) {
    if (nome.length() < 5) {
      return Resultado.falhou("O nome deve conter pelo menos 5 caracteres!");
    }

    Resultado<Banco> encontrouBanco = this._buscarBancoRepository.buscarBanco(nome);

    if (encontrouBanco.falhou()) {
      return Resultado.falhou("Banco não encontrado!");
    }

    Optional<Banco> b = encontrouBanco.pegarValor();
    return Resultado.sucesso(new BancoDTO(b.get().getNome(), b.get().getAgencia()));
  }
}
