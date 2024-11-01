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
}
