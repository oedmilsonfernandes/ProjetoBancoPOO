package UseCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.projectpracticaldev.projetobancopoo.UseCases.BuscarBanco;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.BuscarBancoRepository;
import com.projectpracticaldev.projetobancopoo.UseCases.Protocols.CadastrarBancoRepository;
import com.projectpracticaldev.projetobancopoo.DTO.BancoDTO;
import com.projectpracticaldev.projetobancopoo.Entity.Banco;
import com.projectpracticaldev.projetobancopoo.Entity.Resultado;
import com.projectpracticaldev.projetobancopoo.Infra.Repository.BancoRepositoryInMemory;

public class BuscarBancoTest {

    @Test
    public void naoDeveEncontrarBancoPorqueNomeTemTamanhoMenorQueCinco(){
        BuscarBancoRepository bbr = new BancoRepositoryInMemory();
        BuscarBanco bb = new BuscarBanco(bbr);

        String nome = "Banc";

        Resultado<BancoDTO> bancoEncontrado = bb.execute(nome);

        assertEquals(false, bancoEncontrado.sucesso());
        assertEquals("O nome deve conter pelo menos 5 caracteres!", bancoEncontrado.pegarMensagemDeErro());
    }

    @Test
    public void naoDeveEncontrarBancoPorqueNaoExiste(){
        BuscarBancoRepository bbr = new BancoRepositoryInMemory();
        BuscarBanco bb = new BuscarBanco(bbr);

        String nome = "Banco Test";

        Resultado<BancoDTO> bancoEncontrado = bb.execute(nome);

        assertEquals(false, bancoEncontrado.sucesso());
        assertEquals("Banco não encontrado!", bancoEncontrado.pegarMensagemDeErro());
    }

    @Test
    public void deveEncontrarBanco(){
        BuscarBancoRepository bbr = new BancoRepositoryInMemory();
        Banco b = Banco.criar("Banco Test", 200); 
        ((CadastrarBancoRepository)bbr).salvar(b);

        BuscarBanco bb = new BuscarBanco(bbr);

        String nome = "Banco Test";

        Resultado<BancoDTO> bancoEncontrado = bb.execute(nome);

        assertEquals(true, bancoEncontrado.sucesso());
        assertEquals("Banco Test", bancoEncontrado.pegarValor().get().nome());
    }

}
