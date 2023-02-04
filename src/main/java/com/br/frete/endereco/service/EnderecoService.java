package com.br.frete.endereco.service;

import com.br.frete.endereco.feign.EnderecoFeign;
import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.model.dto.EnderecoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoFeign enderecoFeign;

    public EnderecoResponse executa(EnderecoRequest request){
        return enderecoFeign.buscaEnderecoCeo(request.getCep());
    }
}
