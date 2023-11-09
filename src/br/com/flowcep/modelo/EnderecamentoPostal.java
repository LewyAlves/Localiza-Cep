package br.com.flowcep.modelo;

import br.com.flowcep.record.ViaCep;

public class EnderecamentoPostal {
    private String estado;
    private String cidade;
    private String cep;
    private String ddd;


    public  EnderecamentoPostal (ViaCep viaCep){
        this.cep = viaCep.cep();
        this.cidade = viaCep.localidade();
        this.estado = viaCep.uf();
        this.ddd = viaCep.ddd();
    }


    @Override
    public String toString() {
        return "(" + "Endereçamento postal = "+ "Cep: "+ cep + ", Estado: " + estado + ", Cidade: "  +  cidade + ", ddd: " + ddd + ")";
    }
}
