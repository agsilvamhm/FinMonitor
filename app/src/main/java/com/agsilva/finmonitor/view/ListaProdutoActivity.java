package com.agsilva.finmonitor.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    public static void nova(AppCompatActivity activity){
        Intent intent = new Intent(activity, ListaProdutoActivity.class);
        activity.startActivity(intent);
    }

    private ListView listaViewProdutos;
    private ArrayList<Produto> listaProdutos ;
    private  ArrayAdapter<Produto> listaAdapter ;
    private ProdutoAdapter adapter;

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

    ActivityResultLauncher<Intent> laucherNovoProduto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();
                        if (bundle != null){
                            String nome = bundle.getString(ProdutoActivity.NOME);
                            String codigo = bundle.getString(ProdutoActivity.CODIGO);
                           // String risco = bundle.getString(String.valueOf(ProdutoActivity.RISCO));
                          //  String tipo = bundle.getString(ProdutoActivity.TIPO);
                          //  String ativo = bundle.getString(String.valueOf(ProdutoActivity.ATIVO));
                            Produto produto = new Produto(nome,codigo, ProdutoRisco.ALTO, new TipoProduto("Tese"), true);

                          //  Produto produto = (Produto) intent.getSerializableExtra("produto");
                            listaProdutos.add(produto);
                            //listaAdapter.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    private void popularLista(){
        listaProdutos = new ArrayList<>();
        listaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProdutos);
        adapter = new ProdutoAdapter(this, listaProdutos);
       // listaViewProdutos.setAdapter(listaAdapter);
        listaViewProdutos.setAdapter(adapter);

     /*   String[] nomes = getResources().getStringArray(R.array.nomes);
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
        listaViewProdutos.setAdapter(adapter); */
    }

    public void menuCadastro(View view){
    //    Intent intent = new Intent(this, ProdutoActivity.class);
      //  laucherNovoProduto.launch(intent);
        //ProdutoActivity.nova(this);
        ProdutoActivity.nova(this, laucherNovoProduto);
    }
}