package Entity;

import org.junit.jupiter.api.Test;

import com.projectpracticaldev.projetobancopoo.Entity.ContaPoupanca;
import com.projectpracticaldev.projetobancopoo.Entity.DadosBancarios;
import com.projectpracticaldev.utils.Resultado;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ContaPoupancaTest {

    private ContaPoupanca createConta(){
        return new ContaPoupanca(1,1,"Teste",0.0,true,new ArrayList<>(), null, 0.005);
    }

    @Test
    public void deveDepositar(){
        ContaPoupanca c = createConta();
        Resultado<Boolean> depositou = c.depositar(100.0);

        assertEquals(true, depositou.retorno());
        assertEquals(100.0, c.getSaldo());
        assertEquals("Deposito", c.getListaHistorico().get(0).tipoMovimentacao());
        assertEquals(100.0, c.getListaHistorico().get(0).valor());
        assertTrue(c.getDataUltimaMovimentacao() != null, "Não foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveDepositarPorTerValorNegativo(){
        ContaPoupanca c = createConta();
        Resultado<Boolean> depositou = c.depositar(-100.0);

        assertEquals(false, depositou.retorno());
        assertEquals("O valor do deposito é negativo!", depositou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveSacarPorTerValorNegativo(){
        ContaPoupanca c = createConta();
        Resultado<Boolean> sacou = c.sacar(-100.0);

        assertEquals(false, sacou.retorno());
        assertEquals("O valor do saque é negativo!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveSacarPorqueNaoTemSaldoSuficiente(){
        ContaPoupanca c = createConta();
        Resultado<Boolean> sacou = c.sacar(100.0);

        assertEquals(false, sacou.retorno());
        assertEquals("A conta não tem saldo para esse saque!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void deveSacar(){
        ContaPoupanca c = createConta();
        c.depositar(100.0);

        Resultado<Boolean> sacou = c.sacar(100.0);

        assertEquals(true, sacou.retorno());
        assertEquals(0, c.getSaldo());
        assertEquals("Saque", c.getListaHistorico().get(1).tipoMovimentacao());
        assertEquals(100.0, c.getListaHistorico().get(1).valor());
        assertTrue(c.getDataUltimaMovimentacao() != null, "Não foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveTransferirPorTerValorNegativo(){
        ContaPoupanca c = createConta();
        ContaPoupanca aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(-100.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("O valor da transferencia é negativo!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveTransferirPorqueNaoTemSaldoSuficiente(){
        ContaPoupanca c = createConta();
        ContaPoupanca aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(100.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("A conta não tem saldo para essa transferencia!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void deveTransferir(){
        ContaPoupanca c = createConta();
        ContaPoupanca c2 = createConta();
        c.setSaldo(100.0);

        Resultado<Boolean> transferiu = c.transferir(100.0, c2);

        assertEquals(true, transferiu.retorno());
        assertEquals(false, c.getListaHistorico().isEmpty());
        assertEquals(true, c.getListaHistorico().get(0).tipoMovimentacao().startsWith("Transferencia para"));
        assertEquals(100.0, c.getListaHistorico().get(0).valor());
        assertEquals(0.0, c.getSaldo());
        assertEquals(false, c2.getListaHistorico().isEmpty());
        assertEquals(true, c2.getListaHistorico().get(0).tipoMovimentacao().startsWith("Transferencia de"));
        assertEquals(100.0, c2.getListaHistorico().get(0).valor());
        assertEquals(100.0, c2.getSaldo());
        assertTrue(c.getDataUltimaMovimentacao() != null, "Não foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveReceberTransferenciaPorTerValorNegativo(){
        ContaPoupanca c = createConta();
        DadosBancarios destino = new DadosBancarios(2, 2, "Carinha que mora logo ali");
        Resultado<Boolean> recebeuTansferencia = c.receberTransferencia(-100.0, destino);

        assertEquals(false, recebeuTansferencia.retorno());
        assertEquals("O valor da transferencia é negativo!", recebeuTansferencia.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void deveReceberTransferencia(){
        ContaPoupanca c = createConta();
        DadosBancarios destino = new DadosBancarios(2, 2, "Carinha que mora logo ali");
        Resultado<Boolean> recebeuTansferencia = c.receberTransferencia(100.0, destino);

        assertEquals(true, recebeuTansferencia.retorno());
        assertEquals(100.0, c.getSaldo());
        assertEquals(false, c.getListaHistorico().isEmpty());
        assertEquals(true, c.getListaHistorico().get(0).tipoMovimentacao().startsWith("Transferencia de {numero: 2, agencia: 2, cliente: Carinha que mora logo ali}"));
        assertEquals(100.0, c.getListaHistorico().get(0).valor());
        assertTrue(c.getDataUltimaMovimentacao() != null, "Não foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveBloquearPorqueTemSaldo(){
        ContaPoupanca c = createConta();
        c.depositar(100.0);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(false, bloqueou.retorno());
        assertEquals("A conta não pode ser bloqueada, pois ainda há saldo!", bloqueou.erro());
        assertTrue(c.getDataUltimaMovimentacao() != null, "Não foi setada a data da ultima movimentação");
    }

    @Test
    public void naoDeveBloquearPorqueJaEstaBloqueada(){
        ContaPoupanca c = createConta();
        c.setAtiva(false);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(false, bloqueou.retorno());
        assertEquals("A conta não pode ser bloqueada, pois já está bloqueada!", bloqueou.erro());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

    @Test
    public void deveBloquear(){
        ContaPoupanca c = createConta();
        c.depositar(100.0);
        c.sacar(100.0);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(true, bloqueou.retorno());
        assertEquals(false, c.getAtiva());
        assertTrue(c.getDataUltimaMovimentacao() == null, "Foi setada a data da ultima movimentação");
    }

}
