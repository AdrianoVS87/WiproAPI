package com.br.frete.endereco.controller;

import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/consulta")
    @Operation(summary = "Buscar o endereço e valor do frete pelo CEP", description = "Esta operação retorna os dados de endereço e valor do frete do referido CEP.")
    public ResponseEntity consultaCep(@RequestBody EnderecoRequest enderecoRequest) {
        return ResponseEntity.ok(enderecoService.executa(enderecoRequest));
    }
}
