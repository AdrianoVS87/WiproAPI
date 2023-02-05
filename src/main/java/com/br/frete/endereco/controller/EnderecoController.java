package com.br.frete.endereco.controller;

import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/consulta")
    public ResponseEntity consultaCep(@RequestBody EnderecoRequest enderecoRequest) {
        return ResponseEntity.ok(enderecoService.executa(enderecoRequest));
    }
}
