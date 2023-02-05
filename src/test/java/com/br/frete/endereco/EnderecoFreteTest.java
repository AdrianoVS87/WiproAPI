package com.br.frete.endereco;

import com.br.frete.endereco.model.dto.EnderecoFrete;
import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.service.EnderecoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class EnderecoFreteTest {

    @Autowired
    private EnderecoService service;

    @Test
    public void buscarDadosFreteComCepValidoTest1(){
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep("90480-200");

        String resposta = service.executa(enderecoRequest);

        EnderecoFrete enderecoFrete = new EnderecoFrete();
        enderecoFrete.setCep("90480-200");
        enderecoFrete.setRua("Rua Anita Garibaldi");
        enderecoFrete.setComplemento("de 1240 ao fim - lado par");
        enderecoFrete.setBairro("Boa Vista");
        enderecoFrete.setCidade("Porto Alegre");
        enderecoFrete.setEstado("RS");
        enderecoFrete.setFrete(17.3);

        assertEquals(resposta, enderecoFrete.toString());
    }

    @Test
    public void buscarDadosFreteComCepValidoTest2(){
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep("20251061");

        String resposta = service.executa(enderecoRequest);

        EnderecoFrete enderecoFrete = new EnderecoFrete();
        enderecoFrete.setCep("20251-061");
        enderecoFrete.setRua("Rua Barão de Petrópolis");
        enderecoFrete.setComplemento("até 499 - lado ímpar");
        enderecoFrete.setBairro("Rio Comprido");
        enderecoFrete.setCidade("Rio de Janeiro");
        enderecoFrete.setEstado("RJ");
        enderecoFrete.setFrete(7.85);

        assertEquals(resposta, enderecoFrete.toString());
    }

    @Test
    public void buscarDadosFreteComCepNaoExistenteTest(){
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep("90480-666");

        String resposta = service.executa(enderecoRequest);

        String mensagemErro = "CEP não encontrado, por favor repetir a consulta com dados válidos.";

        assertEquals(resposta, mensagemErro);
    }

    @Test
    public void buscarDadosFreteComDadosInvalidosTest(){
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCep("90480ABC");

        String resposta = service.executa(enderecoRequest);

        String mensagemErro = "CEP inválido, por favor repetir a consulta com dados válidos.";

        assertEquals(resposta, mensagemErro);
    }

}
