package br.com.localizacep.main;

import br.com.localizacep.modelo.EnderecamentoPostal;
import br.com.localizacep.record.ViaCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buscas {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Bem vindo ao LocalizaCep, seu buscador de cep");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<String> listaDeCep = new ArrayList<>();
        String buscaCep = "";

        while (!buscaCep.equalsIgnoreCase("sair")) {
            System.out.println("digite o cep que deseja buscar");
            buscaCep = leitor.nextLine();

            try {
                if (buscaCep.equalsIgnoreCase("sair")){
                    break;
                }

                var cep = "https://viacep.com.br/ws/" + buscaCep.replace("-", "") + "/json/";

                System.out.println(cep);


                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(cep))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                ViaCep viaCep = gson.fromJson(json, ViaCep.class);
                EnderecamentoPostal enderecamentoPostal = new EnderecamentoPostal(viaCep);

                listaDeCep.add(String.valueOf(enderecamentoPostal));
                System.out.println(enderecamentoPostal);
            } catch (JsonSyntaxException e){
                System.out.println("Desculpe não consegui buscar com esse endereço, tente novamente!");
            }

        }
        System.out.println(listaDeCep);

        FileWriter arquivo = new FileWriter("cepBuscados");
        arquivo.write(gson.toJson(listaDeCep));
        arquivo.close();
    }
}
