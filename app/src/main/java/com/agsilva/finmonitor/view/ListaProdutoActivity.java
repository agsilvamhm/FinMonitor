package com.agsilva.finmonitor.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.agsilva.finmonitor.R;
import com.agsilva.finmonitor.modelo.Produto;
import com.agsilva.finmonitor.modelo.ProdutoRisco;
import com.agsilva.finmonitor.modelo.TipoProduto;
import com.agsilva.finmonitor.util.ProdutoAdapter;

import java.util.ArrayList;

public class ListaProdutoActivity extends AppCompatActivity {
    private ListView listaViewProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaViewProdutos = findViewById(R.id.listaViewProdutos);

        listaViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produto produto = (Produto) listaViewProdutos.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), getString(R.string.o_produto) + produto.getNome() + getString(R.string.foi_clicado), Toast.LENGTH_LONG).show();
            }
        });

        popularLista();
    }

    private void popularLista(){
        String[] nomes = getResources().getStringArray(R.array.nomes);
        String[] codigos = getResources().getStringArray(R.array.codigos);
        String[] tipos = getResources().getStringArray(R.array.tipos);
        int[] risco = getResources().getIntArray(R.array.tipo_risco);

        Produto produto = null;
        ArrayList<Produto> produtos = new ArrayList<>();

        for (int cont=0; cont < nomes.length; cont++){
            produtos.add(new Produto(nomes[cont],codigos[cont], ProdutoRisco.values()[risco[cont]], new TipoProduto(tipos[cont]),false));
        }

      //  ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);
        ProdutoAdapter adapter = new ProdutoAdapter(this, produtos);
        listaViewProdutos.setAdapter(adapter);
    }
}