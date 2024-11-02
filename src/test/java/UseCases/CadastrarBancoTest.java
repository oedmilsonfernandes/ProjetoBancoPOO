package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Infra.Repository.BancoRepositoryInMemory;
import com.projectpracticaldev.projetobancopoo.UseCases.CadastrarBanco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.utils.Resultado;

public class CadastrarBancoTest {

    @Test
    public void deveCadastrarUmBanco(){
        CadastrarBancoRepository cbr = new BancoRepositoryInMemory();
        CadastrarBanco cadastrarBanco = new CadastrarBanco(cbr);

        String nome = "Banco Test";
        Integer agencia = 200;
        
        Resultado<Boolean> output = cadastrarBanco.execute(nome, agencia);

        assertEquals(true, output.retorno());
    }

    @Test
    public void naoDeveCadastrarUmBancoPorqueNomeTemTamanhoMenorQueCinco(){
        CadastrarBancoRepository cbr = new BancoRepositoryInMemory();
        CadastrarBanco cadastrarBanco = new CadastrarBanco(cbr);

        String nome = "";
        Integer agencia = 200;
        
        Resultado<Boolean> output = cadastrarBanco.execute(nome, agencia);

        assertEquals(false, output.retorno());
        assertEquals("O nome deve conter pelo menos 5 caracteres!", output.erro());
    }

    @Test
    public void naoDeveCadastrarUmBancoPorqueNomeJaExiste(){
        CadastrarBancoRepository cbr = new BancoRepositoryInMemory();
        Banco aux = Banco.criar("Banco Test", 200);
        cbr.salvar(aux);
        CadastrarBanco cadastrarBanco = new CadastrarBanco(cbr);

        String nome = "Banco Test";
        Integer agencia = 200;
        
        Resultado<Boolean> output = cadastrarBanco.execute(nome, agencia);

        assertEquals(false, output.retorno());
        assertEquals("Esse nome de banco já existe!", output.erro());
    }

    @Test
    public void naoDeveCadastrarUmBancoPorqueAgenciaNegativo(){
        CadastrarBancoRepository cbr = new BancoRepositoryInMemory();
        CadastrarBanco cadastrarBanco = new CadastrarBanco(cbr);

        String nome = "Banco Test";
        Integer agencia = -200;
        
        Resultado<Boolean> output = cadastrarBanco.execute(nome, agencia);

        assertEquals(false, output.retorno());
        assertEquals("O Numero da agencia é negativo!", output.erro());
    }

}
