package Entity;

import org.junit.jupiter.api.Test;

import com.projectpracticaldev.projetobancopoo.Entity.ContaBancaria;
import com.projectpracticaldev.utils.Resultado;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ContaBancariaTest {

    private ContaBancaria createConta(){
        return new ContaBancaria(1,1,"Teste",0.0,true,new ArrayList<>());
    }

    @Test
    public void deveDepositar(){
        ContaBancaria c = createConta();
        Resultado<Boolean> depositou = c.depositar(100.0);

        assertEquals(true, depositou.retorno());
        assertEquals(100.0, c.getSaldo());
        assertEquals("Deposito", c.getListaHistorico().get(0).tipoMovimentacao());
        assertEquals(100.0, c.getListaHistorico().get(0).valor());
    }

    @Test
    public void naoDeveDepositarPorTerValorNegativo(){
        ContaBancaria c = createConta();
        Resultado<Boolean> depositou = c.depositar(-100.0);

        assertEquals(false, depositou.retorno());
        assertEquals("O valor do deposito é negativo!", depositou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void naoDeveSacarPorTerValorNegativo(){
        ContaBancaria c = createConta();
        Resultado<Boolean> sacou = c.sacar(-100.0);

        assertEquals(false, sacou.retorno());
        assertEquals("O valor do saque é negativo!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void naoDeveSacarPorqueNaoTemSaldoSuficiente(){
        ContaBancaria c = createConta();
        Resultado<Boolean> sacou = c.sacar(100.0);

        assertEquals(false, sacou.retorno());
        assertEquals("A conta não tem saldo para esse saque!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void deveSacar(){
        ContaBancaria c = createConta();
        c.depositar(100.0);

        Resultado<Boolean> sacou = c.sacar(100.0);

        assertEquals(true, sacou.retorno());
        assertEquals(0, c.getSaldo());
        assertEquals("Saque", c.getListaHistorico().get(1).tipoMovimentacao());
        assertEquals(100.0, c.getListaHistorico().get(1).valor());
    }

    @Test
    public void naoDeveTransferirPorTerValorNegativo(){
        ContaBancaria c = createConta();
        ContaBancaria aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(-100.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("O valor da transferencia é negativo!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void naoDeveTransferirPorqueNaoTemSaldoSuficiente(){
        ContaBancaria c = createConta();
        ContaBancaria aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(100.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("A conta não tem saldo para essa transferencia!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void deveTransferir(){
        ContaBancaria c = createConta();
        ContaBancaria c2 = createConta();
        c.depositar(100.0);

        Resultado<Boolean> transferiu = c.transferir(100.0, c2);

        assertEquals(true, transferiu.retorno());
        assertEquals(false, c.getListaHistorico().isEmpty());
        assertEquals(true, c.getListaHistorico().get(1).tipoMovimentacao().startsWith("Transferencia para"));
        assertEquals(100.0, c.getListaHistorico().get(1).valor());
        assertEquals(0.0, c.getSaldo());
        assertEquals(false, c2.getListaHistorico().isEmpty());
        assertEquals(true, c2.getListaHistorico().get(0).tipoMovimentacao().startsWith("Transferencia de"));
        assertEquals(100.0, c2.getListaHistorico().get(0).valor());
        assertEquals(100.0, c2.getSaldo());
    }
}
