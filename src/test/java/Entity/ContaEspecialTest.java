package Entity;

import org.junit.jupiter.api.Test;

import com.projectpracticaldev.projetobancopoo.Entity.ContaEspecial;
import com.projectpracticaldev.utils.Resultado;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ContaEspecialTest {

    private ContaEspecial createConta(){
        return new ContaEspecial(1,1,"Teste",0.0,true,new ArrayList<>(),500.0);
    }

    @Test
    public void naoDeveSacarPorTerValorNegativo(){
        ContaEspecial c = createConta();
        Resultado<Boolean> sacou = c.sacar(-100.0);

        assertEquals(false, sacou.retorno());
        assertEquals("O valor do saque é negativo!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void naoDeveSacarPorqueNaoTemSaldoSuficiente(){
        ContaEspecial c = createConta();
        Resultado<Boolean> sacou = c.sacar(510.0);

        assertEquals(false, sacou.retorno());
        assertEquals("A conta não tem saldo para esse saque!", sacou.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void deveSacar(){
        ContaEspecial c = createConta();
        c.depositar(100.0);

        Resultado<Boolean> sacou = c.sacar(100.0);

        assertEquals(true, sacou.retorno());
        assertEquals(0, c.getSaldo());
        assertEquals("Saque", c.getListaHistorico().get(1).tipoMovimentacao());
        assertEquals(100.0, c.getListaHistorico().get(1).valor());
    }

    @Test
    public void naoDeveTransferirPorTerValorNegativo(){
        ContaEspecial c = createConta();
        ContaEspecial aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(-100.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("O valor da transferencia é negativo!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void naoDeveTransferirPorqueNaoTemSaldoSuficiente(){
        ContaEspecial c = createConta();
        ContaEspecial aux = createConta();
        Resultado<Boolean> transferiu = c.transferir(510.0, aux);

        assertEquals(false, transferiu.retorno());
        assertEquals("A conta não tem saldo para essa transferencia!", transferiu.erro());
        assertEquals(true, c.getListaHistorico().isEmpty());
    }

    @Test
    public void deveTransferir(){
        ContaEspecial c = createConta();
        ContaEspecial c2 = createConta();
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

    @Test
    public void naoDeveBloquearPorqueTemSaldo(){
        ContaEspecial c = createConta();
        c.depositar(100.0);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(false, bloqueou.retorno());
        assertEquals("A conta não pode ser bloqueada, pois ainda há saldo!", bloqueou.erro());
    }

    @Test
    public void naoDeveBloquearPorqueTemDebito(){
        ContaEspecial c = createConta();
        c.sacar(100.0);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(false, bloqueou.retorno());
        assertEquals("A conta não pode ser bloqueada, pois ainda há debito!", bloqueou.erro());
    }

    @Test
    public void naoDeveBloquearPorqueJaEstaBloqueada(){
        ContaEspecial c = createConta();
        c.setAtiva(false);
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(false, bloqueou.retorno());
        assertEquals("A conta não pode ser bloqueada, pois já está bloqueada!", bloqueou.erro());
    }

    @Test
    public void deveBloquear(){
        ContaEspecial c = createConta();
        Resultado<Boolean> bloqueou = c.bloquearConta();

        assertEquals(true, bloqueou.retorno());
        assertEquals(false, c.getAtiva());
    }
}
