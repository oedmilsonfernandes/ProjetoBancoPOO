package Entity;

import static org.junit.jupiter.api.Assertions.*;

import com.projectpracticaldev.projetobancopoo.Entity.ContaBancaria;
import com.projectpracticaldev.projetobancopoo.Entity.DadosBancarios;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ContaBancariaTest {

  private ContaBancaria createConta() {
    return new ContaBancaria(1, 1, "Teste", 0.0, true, new ArrayList<>());
  }

  @Test
  public void deveDepositar() {
    ContaBancaria c = createConta();
    Resultado<Boolean> depositou = c.depositar(100.0);

    assertEquals(true, depositou.sucesso());
    assertEquals(100.0, c.getSaldo());
    assertEquals("Deposito", c.getListaHistorico().get(0).tipoMovimentacao());
    assertEquals(100.0, c.getListaHistorico().get(0).valor());
  }

  @Test
  public void naoDeveDepositarPorTerValorNegativo() {
    ContaBancaria c = createConta();
    Resultado<Boolean> depositou = c.depositar(-100.0);

    assertEquals(false, depositou.sucesso());
    assertEquals("O valor do deposito é negativo!", depositou.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void naoDeveSacarPorTerValorNegativo() {
    ContaBancaria c = createConta();
    Resultado<Boolean> sacou = c.sacar(-100.0);

    assertEquals(false, sacou.sucesso());
    assertEquals("O valor do saque é negativo!", sacou.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void naoDeveSacarPorqueNaoTemSaldoSuficiente() {
    ContaBancaria c = createConta();
    Resultado<Boolean> sacou = c.sacar(100.0);

    assertEquals(false, sacou.sucesso());
    assertEquals("A conta não tem saldo para esse saque!", sacou.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void deveSacar() {
    ContaBancaria c = createConta();
    c.depositar(100.0);

    Resultado<Boolean> sacou = c.sacar(100.0);

    assertEquals(true, sacou.sucesso());
    assertEquals(0, c.getSaldo());
    assertEquals("Saque", c.getListaHistorico().get(1).tipoMovimentacao());
    assertEquals(100.0, c.getListaHistorico().get(1).valor());
  }

  @Test
  public void naoDeveTransferirPorTerValorNegativo() {
    ContaBancaria c = createConta();
    ContaBancaria aux = createConta();
    Resultado<Boolean> transferiu = c.transferir(-100.0, aux);

    assertEquals(false, transferiu.sucesso());
    assertEquals("O valor da transferencia é negativo!", transferiu.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void naoDeveTransferirPorqueNaoTemSaldoSuficiente() {
    ContaBancaria c = createConta();
    ContaBancaria aux = createConta();
    Resultado<Boolean> transferiu = c.transferir(100.0, aux);

    assertEquals(false, transferiu.sucesso());
    assertEquals(
        "A conta não tem saldo para essa transferencia!", transferiu.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void deveTransferir() {
    ContaBancaria c = createConta();
    ContaBancaria c2 = createConta();
    c.depositar(100.0);

    Resultado<Boolean> transferiu = c.transferir(100.0, c2);

    assertEquals(true, transferiu.sucesso());
    assertEquals(false, c.getListaHistorico().isEmpty());
    assertEquals(
        true, c.getListaHistorico().get(1).tipoMovimentacao().startsWith("Transferencia para"));
    assertEquals(100.0, c.getListaHistorico().get(1).valor());
    assertEquals(0.0, c.getSaldo());
    assertEquals(false, c2.getListaHistorico().isEmpty());
    assertEquals(
        true, c2.getListaHistorico().get(0).tipoMovimentacao().startsWith("Transferencia de"));
    assertEquals(100.0, c2.getListaHistorico().get(0).valor());
    assertEquals(100.0, c2.getSaldo());
  }

  @Test
  public void naoDeveReceberTransferenciaPorTerValorNegativo() {
    ContaBancaria c = createConta();
    DadosBancarios destino = new DadosBancarios(2, 2, "Carinha que mora logo ali");
    Resultado<Boolean> recebeuTansferencia = c.receberTransferencia(-100.0, destino);

    assertEquals(false, recebeuTansferencia.sucesso());
    assertEquals("O valor da transferencia é negativo!", recebeuTansferencia.pegarMensagemDeErro());
    assertEquals(true, c.getListaHistorico().isEmpty());
  }

  @Test
  public void deveReceberTransferencia() {
    ContaBancaria c = createConta();
    DadosBancarios destino = new DadosBancarios(2, 2, "Carinha que mora logo ali");
    Resultado<Boolean> recebeuTansferencia = c.receberTransferencia(100.0, destino);

    assertEquals(true, recebeuTansferencia.sucesso());
    assertEquals(100.0, c.getSaldo());
    assertEquals(false, c.getListaHistorico().isEmpty());
    assertEquals(
        true,
        c.getListaHistorico()
            .get(0)
            .tipoMovimentacao()
            .startsWith(
                "Transferencia de {numero: 2, agencia: 2, cliente: Carinha que mora logo ali}"));
    assertEquals(100.0, c.getListaHistorico().get(0).valor());
  }

  @Test
  public void naoDeveBloquearPorqueTemSaldo() {
    ContaBancaria c = createConta();
    c.depositar(100.0);
    Resultado<Boolean> bloqueou = c.bloquearConta();

    assertEquals(false, bloqueou.sucesso());
    assertEquals(
        "A conta não pode ser bloqueada, pois ainda há saldo!", bloqueou.pegarMensagemDeErro());
  }

  @Test
  public void naoDeveBloquearPorqueJaEstaBloqueada() {
    ContaBancaria c = createConta();
    c.setAtiva(false);
    Resultado<Boolean> bloqueou = c.bloquearConta();

    assertEquals(false, bloqueou.sucesso());
    assertEquals(
        "A conta não pode ser bloqueada, pois já está bloqueada!", bloqueou.pegarMensagemDeErro());
  }

  @Test
  public void deveBloquear() {
    ContaBancaria c = createConta();
    Resultado<Boolean> bloqueou = c.bloquearConta();

    assertEquals(true, bloqueou.sucesso());
    assertEquals(false, c.getAtiva());
  }

  @Test
  public void naoDeveDesbloquearPorqueJaEstaDesbloqueada() {
    ContaBancaria c = createConta();
    Resultado<Boolean> desbloqueou = c.desbloquearConta();

    assertEquals(false, desbloqueou.sucesso());
    assertEquals(
        "A conta não pode ser desbloqueada, pois já está desbloqueada!",
        desbloqueou.pegarMensagemDeErro());
  }

  @Test
  public void deveDesbloquear() {
    ContaBancaria c = createConta();
    c.setAtiva(false);
    Resultado<Boolean> desbloqueou = c.desbloquearConta();

    assertEquals(true, desbloqueou.sucesso());
    assertEquals(true, c.getAtiva());
  }
}
