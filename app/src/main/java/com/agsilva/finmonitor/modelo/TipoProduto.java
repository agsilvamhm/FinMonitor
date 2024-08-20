package com.agsilva.finmonitor.modelo;

public class TipoProduto {
    private String nome;

    public TipoProduto(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoProduto{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
