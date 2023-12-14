package br.com.localizacep.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    public String obterJsonViaCep(String cep) throws IOException, InterruptedException {
        var buscaCep = cep;
        var url = "https://viacep.com.br/ws/" + buscaCep.replace("-", "") + "/json/";

        System.out.println(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
