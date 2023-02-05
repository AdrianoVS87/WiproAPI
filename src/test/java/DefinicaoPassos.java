import com.br.frete.endereco.model.dto.EnderecoFrete;
import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.service.EnderecoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

public class DefinicaoPassos {

    private EnderecoService service;
    private String resposta;

    @Dado("Que a minha aplicação esta inicializada")
    public void queAMinhaAplicaçãoEstaInicializada() {
        service = new EnderecoService();
    }

    @Quando("Eu pesquiso o cep {string}")
    public void euPesquisoOCep(String cep) {
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep(cep);
        resposta = service.executa(enderecoRequest);
    }

    @Então("Eu encontro o frete")
    public void euEncontroOFrete() {
        Assert.assertTrue(resposta.contains("frete"));
    }

    @Então("Eu recebo mensagem de erro CEP não encontrado")
    public void euReceboMensagemDeErroCEPNãoEncontrado() {
        String mensagem = "CEP não encontrado, por favor repetir a consulta com dados válidos.";
        Assert.assertEquals(resposta, mensagem);
    }

    @Então("Eu recebo mensagem de erro CEP inválido")
    public void euReceboMensagemDeErroCEPInválido() {
        String mensagem = "CEP inválido, por favor repetir a consulta com dados válidos.";
        Assert.assertEquals(resposta, mensagem);
    }
}
