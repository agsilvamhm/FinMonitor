package com.agsilva.finmonitor.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
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

    private Produto produtoOriginal;

    private ListView listaViewProdutos;
    private ArrayList<Produto> listaProdutos ;
    private ProdutoAdapter adapter;

    private ActionMode actionMode;
    private View viewSelecionada;
    private int posicaoSelecionada = -1;
    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.menu_lista_produto_crud, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int idMenuItem = item.getItemId();

            if (idMenuItem == R.id.menuItemEditar){
                //ProdutoActivity.nova(ListaProdutoActivity.this, editarProduto);
                editarProduto(produtoOriginal);
                mode.finish();
                return true;
            } else if (idMenuItem == R.id.menuItemExcluir){
                excluirPessoa();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            viewSelecionada = null;
            listaViewProdutos.setEnabled(true);
        }
    };

    private void excluirPessoa(){
        listaProdutos.remove(posicaoSelecionada);
        adapter.notifyDataSetChanged();
    }

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

        listaViewProdutos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listaViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (actionMode != null){
                    return false;
                }
                posicaoSelecionada = position;
              //  viewSelecionada.setBackgroundColor(Color.LTGRAY);
                viewSelecionada = view;
                listaViewProdutos.setEnabled(false);
                actionMode = startSupportActionMode(callback);
                produtoOriginal = listaProdutos.get(position);
                return false;
            }
        });

        listaViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produto produto = (Produto) listaViewProdutos.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), getString(R.string.o_produto) + produto.getNome() + getString(R.string.foi_clicado), Toast.LENGTH_LONG).show();
                posicaoSelecionada = position;
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
                            Produto produto = intent.getParcelableExtra("produto");
                            listaProdutos.add(produto);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

  private void editarProduto(Produto produtoOriginal) {
      Intent intent = new Intent(this, ProdutoActivity.class);
      intent.putExtra("produto", produtoOriginal);
      editarProduto.launch(intent);
  }

    ActivityResultLauncher<Intent> editarProduto = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();

                        if (bundle != null) {
                            Produto produtoEditado = intent.getParcelableExtra("produto");
                            listaProdutos.set(posicaoSelecionada, produtoEditado);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    private void popularLista(){
        listaProdutos = new ArrayList<>();
        adapter = new ProdutoAdapter(this, listaProdutos);
        listaViewProdutos.setAdapter(adapter);
    }

    public void menuCadastro(View view){
        ProdutoActivity.nova(this, laucherNovoProduto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemAdicionar){
            ProdutoActivity.nova(this, laucherNovoProduto);
            return true;
       } else if (idMenuItem == R.id.menuItemSobre){
            SobreActivity.nova(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}