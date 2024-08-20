package com.agsilva.finmonitor.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agsilva.finmonitor.R;
import com.agsilva.finmonitor.modelo.Produto;
import com.agsilva.finmonitor.modelo.ProdutoRisco;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {
    private Context context;
    private List<Produto> produtos;

    public static class ProdutoHolder{
        public TextView textViewNome;
        public TextView textViewCodigo;
        public TextView textViewRisco;
        public TextView textViewTipo;

    }

    public ProdutoAdapter(Context context, List<Produto> produtos){
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProdutoHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_view_produtos, viewGroup, false);

            holder = new ProdutoHolder();
            holder.textViewNome = view.findViewById(R.id.textViewNome);
            holder.textViewCodigo = view.findViewById(R.id.textViewCodigo);
            holder.textViewRisco = view.findViewById(R.id.textViewRisco);
            holder.textViewTipo = view.findViewById(R.id.textViewTipo);

            view.setTag(holder);
        } else {
            holder = (ProdutoHolder) view.getTag();
        }
        holder.textViewNome.setText(produtos.get(i).getNome());
        holder.textViewCodigo.setText(produtos.get(i).getCodigo());
        holder.textViewRisco.setText(produtos.get(i).getRisco().name());
        holder.textViewTipo.setText(produtos.get(i).getTipo().getNome());
        return view;
    }
}
