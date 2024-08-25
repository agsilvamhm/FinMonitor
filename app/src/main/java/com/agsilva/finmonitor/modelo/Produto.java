package com.agsilva.finmonitor.modelo;

import java.io.Serializable;

public class Produto  {
    private String nome;
    private String codigo;
    private ProdutoRisco risco;
    private TipoProduto tipo;
    private Boolean ativado;

    public Produto(String nome, String codigo, ProdutoRisco risco, TipoProduto tipo, Boolean ativado) {
        this.nome = nome;
        this.codigo = codigo;
        this.risco = risco;
        this.tipo = tipo;
        this.ativado = ativado;
    }

    public Produto(String nome, String codigo){
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ProdutoRisco getRisco() {
        return risco;
    }

    public void setRisco(ProdutoRisco risco) {
        this.risco = risco;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", risco=" + risco +
                ", tipo=" + tipo +
                ", ativado=" + ativado +
                '}';
    }
}
