package com.agsilva.finmonitor.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {
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

    protected Produto(Parcel in) {
        nome = in.readString();
        codigo = in.readString();
        risco = ProdutoRisco.valueOf(in.readString());
        tipo = new TipoProduto((in.readString()));
        byte tmpAtivado = in.readByte();
        ativado = tmpAtivado == 0 ? null : tmpAtivado == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(codigo);
        dest.writeString(risco.name());
        dest.writeString(tipo.getNome());
        dest.writeByte((byte) (ativado == null ? 0 : ativado ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

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