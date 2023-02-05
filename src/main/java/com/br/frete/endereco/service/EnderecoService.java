package com.br.frete.endereco.service;

import com.br.frete.endereco.feign.EnderecoFeign;
import com.br.frete.endereco.model.dto.EnderecoFrete;
import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.model.dto.EnderecoResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoFeign enderecoFeign;

    public String executa(EnderecoRequest request){
        try {
            EnderecoResponse enderecoResponse = enderecoFeign.buscaEnderecoCep(request.getCep());

            if(enderecoResponse.isErro()){
                return "CEP não encontrado, por favor repetir a consulta com dados válidos.";
            }

            EnderecoFrete enderecoFrete = new EnderecoFrete(enderecoResponse);

            if (enderecoFrete.getEstado().equals("ES") || enderecoFrete.getEstado().equals("MG") || enderecoFrete.getEstado().equals("RJ") || enderecoFrete.getEstado().equals("SP")){
                enderecoFrete.setFrete(7.85);
            }

            if (enderecoFrete.getEstado().equals("GO") || enderecoFrete.getEstado().equals("MT") || enderecoFrete.getEstado().equals("MS") || enderecoFrete.getEstado().equals("DF")){
                enderecoFrete.setFrete(12.50);
            }

            if (enderecoFrete.getEstado().equals("MA") || enderecoFrete.getEstado().equals("PI") || enderecoFrete.getEstado().equals("CE") || enderecoFrete.getEstado().equals("RN")
             || enderecoFrete.getEstado().equals("PB") || enderecoFrete.getEstado().equals("PE") || enderecoFrete.getEstado().equals("AL") || enderecoFrete.getEstado().equals("SE")
             || enderecoFrete.getEstado().equals("BA")){
                enderecoFrete.setFrete(15.98);
            }

            if (enderecoFrete.getEstado().equals("RS") || enderecoFrete.getEstado().equals("SC") || enderecoFrete.getEstado().equals("PR")){
                enderecoFrete.setFrete(17.30);
            }

            if (enderecoFrete.getEstado().equals("AM") || enderecoFrete.getEstado().equals("PA") || enderecoFrete.getEstado().equals("AC") || enderecoFrete.getEstado().equals("RO")
             || enderecoFrete.getEstado().equals("RR") || enderecoFrete.getEstado().equals("AP") || enderecoFrete.getEstado().equals("TO")){
                enderecoFrete.setFrete(20.83);
            }

            return enderecoFrete.toString();

        } catch (FeignException.BadRequest e) {

                return "CEP inválido, por favor repetir a consulta com dados válidos.";

        }

    }
}
