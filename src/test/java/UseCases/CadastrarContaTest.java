package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.projectpracticaldev.projetobancopoo.DTO.ContaDTO;
import com.projectpracticaldev.projetobancopoo.DTO.ContaDTO.TipoConta;
import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.Infra.Repository.BancoRepositoryInMemory;
import com.projectpracticaldev.projetobancopoo.Infra.Repository.ContaRepositoryInMemory;
import com.projectpracticaldev.projetobancopoo.UseCases.CadastrarConta;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarContaRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.VerificarBancoRepository;
import org.junit.jupiter.api.Test;

public class CadastrarContaTest {

  @Test
  public void naoDeveCadastrarContaPorqueBancoNaoInformado() {
    CadastrarConta cc = new CadastrarConta(null, null);

    ContaDTO conta = new ContaDTO(null, 1, null, "Cleitin Bodega", 100.0, null, null);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.falhou());
    assertEquals("O Banco não foi informado!", contaCadastrada.pegarMensagemDeErro());
  }

  @Test
  public void naoDeveCadastrarContaPorqueBancoNaoExiste() {
    VerificarBancoRepository cbr = new BancoRepositoryInMemory();
    CadastrarConta cc = new CadastrarConta(cbr, null);

    ContaDTO conta = new ContaDTO(null, 1, 200, "Cleitin Bodega", 100.0, null, null);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.falhou());
    assertEquals("O Banco não foi encontrado!", contaCadastrada.pegarMensagemDeErro());
  }

  @Test
  public void naoDeveCadastrarContaPorqueTipoDaContaNaoInformado() {
    VerificarBancoRepository cbr = new BancoRepositoryInMemory();
    Banco b = Banco.criar("Banco Test", 200);
    ((CadastrarBancoRepository) cbr).salvar(b);
    CadastrarContaRepository ccr = new ContaRepositoryInMemory();
    CadastrarConta cc = new CadastrarConta(cbr, ccr);

    ContaDTO conta = new ContaDTO(null, 1, b.getAgencia(), "Cleitin Bodega", 100.0, null, null);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.falhou());
    assertEquals("O tipo da conta não foi informado!", contaCadastrada.pegarMensagemDeErro());
  }

  @Test
  public void deveCadastrarContaBancaria() {
    VerificarBancoRepository cbr = new BancoRepositoryInMemory();
    Banco b = Banco.criar("Banco Test", 200);
    ((CadastrarBancoRepository) cbr).salvar(b);
    CadastrarContaRepository ccr = new ContaRepositoryInMemory();
    CadastrarConta cc = new CadastrarConta(cbr, ccr);

    ContaDTO conta =
        new ContaDTO(
            TipoConta.ContaBancaria, 1, b.getAgencia(), "Cleitin Bodega", 100.0, null, null);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.sucesso());
  }

  @Test
  public void deveCadastrarContaEspecial() {
    VerificarBancoRepository cbr = new BancoRepositoryInMemory();
    Banco b = Banco.criar("Banco Test", 200);
    ((CadastrarBancoRepository) cbr).salvar(b);
    CadastrarContaRepository ccr = new ContaRepositoryInMemory();
    CadastrarConta cc = new CadastrarConta(cbr, ccr);

    ContaDTO conta =
        new ContaDTO(
            TipoConta.ContaEspecial, 1, b.getAgencia(), "Cleitin Bodega", 100.0, b.limite, null);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.sucesso());
  }

  @Test
  public void deveCadastrarContaPoupanca() {
    VerificarBancoRepository cbr = new BancoRepositoryInMemory();
    Banco b = Banco.criar("Banco Test", 200);
    ((CadastrarBancoRepository) cbr).salvar(b);
    CadastrarContaRepository ccr = new ContaRepositoryInMemory();
    CadastrarConta cc = new CadastrarConta(cbr, ccr);

    ContaDTO conta =
        new ContaDTO(
            TipoConta.ContaPoupanca, 1, b.getAgencia(), "Cleitin Bodega", 100.0, null, b.taxaJuros);

    Resultado<Boolean> contaCadastrada = cc.execute(conta);

    assertEquals(true, contaCadastrada.sucesso());
  }
}
