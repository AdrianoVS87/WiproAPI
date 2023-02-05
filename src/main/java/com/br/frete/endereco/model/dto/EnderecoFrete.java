package com.br.frete.endereco.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnderecoFrete {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double frete;

    public EnderecoFrete() {
    }

    public EnderecoFrete(EnderecoResponse enderecoResponse) {
        this.cep = enderecoResponse.getCep();
        this.rua = enderecoResponse.getLogradouro();
        this.complemento = enderecoResponse.getComplemento();
        this.bairro = enderecoResponse.getBairro();
        this.cidade = enderecoResponse.getLocalidade();
        this.estado = enderecoResponse.getUf();
    }

    @Override
    public String toString() {
        return "{\n"+
                "   \"cep\": \"" + cep + "\",\n" +
                "   \"rua\": \"" + rua + "\"\n" +
                "   \"complemento\": \"" + complemento + "\",\n" +
                "   \"bairro\": \"" + bairro + "\",\n" +
                "   \"cidade\": \"" + cidade + "\",\n" +
                "   \"estado\": \"" + estado + "\",\n" +
                "   \"frete\": " + frete + "\n" +
                "}";
    }
}
