package com.agsilva.finmonitor.modelo;

import java.util.Date;

public class Movimento {
    private Administradora administradora;
    private Produto produto;
    private TipoMovimento movimento;
    private Date data;
    private Number valor;

    public Administradora getAdministradora() {
        return administradora;
    }

    public void setAdministradora(Administradora administradora) {
        this.administradora = administradora;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoMovimento getMovimento() {
        return movimento;
    }

    public void setMovimento(TipoMovimento movimento) {
        this.movimento = movimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Number getValor() {
        return valor;
    }

    public void setValor(Number valor) {
        this.valor = valor;
    }
}
