package com.br.frete.endereco.service;

import com.br.frete.endereco.model.dto.EnderecoFrete;
import com.br.frete.endereco.model.dto.EnderecoRequest;
import com.br.frete.endereco.model.dto.EnderecoResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    public String executa(EnderecoRequest request) throws IOException {
        try{
            //**Consumindo API publica externa
            URL url = new URL("https://viacep.com.br/ws/" + request.getCep() + "/json/");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String cep = "";
            StringBuilder jsonCep = new StringBuilder();
            while ((cep = br.readLine()) != null) {
                jsonCep.append(cep);
            }

            EnderecoResponse enderecoResponse = new Gson().fromJson(jsonCep.toString(), EnderecoResponse.class);

            if (enderecoResponse != null && enderecoResponse.isErro()) {
                return "CEP não encontrado, por favor repetir a consulta com dados válidos.";
            }

            EnderecoFrete enderecoFrete = new EnderecoFrete(enderecoResponse);

            if (enderecoFrete.getEstado().equals("ES") || enderecoFrete.getEstado().equals("MG") || enderecoFrete.getEstado().equals("RJ") || enderecoFrete.getEstado().equals("SP")) {
                enderecoFrete.setFrete(7.85);
            }

            else if (enderecoFrete.getEstado().equals("GO") || enderecoFrete.getEstado().equals("MT") || enderecoFrete.getEstado().equals("MS") || enderecoFrete.getEstado().equals("DF")) {
                enderecoFrete.setFrete(12.50);
            }

            else if (enderecoFrete.getEstado().equals("MA") || enderecoFrete.getEstado().equals("PI") || enderecoFrete.getEstado().equals("CE") || enderecoFrete.getEstado().equals("RN")
                    || enderecoFrete.getEstado().equals("PB") || enderecoFrete.getEstado().equals("PE") || enderecoFrete.getEstado().equals("AL") || enderecoFrete.getEstado().equals("SE")
                    || enderecoFrete.getEstado().equals("BA")) {
                enderecoFrete.setFrete(15.98);
            }

            else if (enderecoFrete.getEstado().equals("RS") || enderecoFrete.getEstado().equals("SC") || enderecoFrete.getEstado().equals("PR")) {
                enderecoFrete.setFrete(17.30);
            }

            else if (enderecoFrete.getEstado().equals("AM") || enderecoFrete.getEstado().equals("PA") || enderecoFrete.getEstado().equals("AC") || enderecoFrete.getEstado().equals("RO")
                    || enderecoFrete.getEstado().equals("RR") || enderecoFrete.getEstado().equals("AP") || enderecoFrete.getEstado().equals("TO")) {
                enderecoFrete.setFrete(20.83);
            }

            return enderecoFrete.toString();

        } catch (Exception ex) {
            return "CEP inválido, por favor repetir a consulta com dados válidos.";
        }
    }
}
