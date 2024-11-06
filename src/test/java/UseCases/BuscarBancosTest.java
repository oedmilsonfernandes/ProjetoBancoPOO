package UseCases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.projectpracticaldev.projetobancopoo.DTO.BancoDTO;
import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.Infra.Repository.BancoRepositoryInMemory;
import com.projectpracticaldev.projetobancopoo.UseCases.BuscarBancos;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancosRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BuscarBancosTest {

  @Test
  public void deveBuscarBancos() {
    BuscarBancosRepository bbrs = new BancoRepositoryInMemory();

    Banco b1 = Banco.criar("Banco1", 1);
    ((BancoRepositoryInMemory) bbrs).salvar(b1);
    Banco b2 = Banco.criar("Banco2", 2);
    ((BancoRepositoryInMemory) bbrs).salvar(b2);

    BuscarBancos bbs = new BuscarBancos(bbrs);

    Resultado<List<BancoDTO>> bancos = bbs.execute();

    assertTrue(bancos.pegarValor().get().size() > 1, "Bancos não retornados!");
  }
}
