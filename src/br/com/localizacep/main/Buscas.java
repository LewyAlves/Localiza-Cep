package br.com.localizacep.main;

import br.com.localizacep.API.Http;
import br.com.localizacep.modelo.EnderecamentoPostal;
import br.com.localizacep.record.ViaCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buscas {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);
        Http http = new Http();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("Bem vindo ao LocalizaCep, seu buscador de cep");
        List<String> listaDeCep = new ArrayList<>();
        String cepPesquisado;

        while (true) {
            System.out.println("digite o cep que deseja buscar");
            cepPesquisado = leitor.nextLine();

            try {
                if (cepPesquisado.equalsIgnoreCase("sair")){
                    break;
                }

                String chamadaDaApi = http.obterJsonViaCep(cepPesquisado);

                ViaCep viaCep = gson.fromJson(chamadaDaApi, ViaCep.class);
                EnderecamentoPostal enderecamentoPostal = new EnderecamentoPostal(viaCep);

                listaDeCep.add(String.valueOf(enderecamentoPostal));
                System.out.println(enderecamentoPostal);
            } catch (JsonSyntaxException e){
                System.out.println("Desculpe não consegui buscar com esse endereço, tente novamente!");
            }

        }
        System.out.println(listaDeCep);

        try (FileWriter arquivo = new FileWriter("cepBuscados")) {
            arquivo.write(gson.toJson(listaDeCep));
        }
    }
}
