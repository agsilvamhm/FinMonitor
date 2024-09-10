package com.agsilva.finmonitor.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.agsilva.finmonitor.R;
import com.agsilva.finmonitor.modelo.Produto;
import com.agsilva.finmonitor.modelo.ProdutoRisco;
import com.agsilva.finmonitor.modelo.TipoProduto;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    public static void nova(AppCompatActivity activity, ActivityResultLauncher<Intent> laucher){
        Intent intent = new Intent(activity, ProdutoActivity.class);
        laucher.launch(intent);
    }

    private ArrayList<String> lista;
    private Spinner spinnerTipo;
    private EditText editNome, editCodigo;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private ProdutoRisco risco ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerTipo = findViewById(R.id.spinnerTipo);
        editNome = findViewById(R.id.editNome);
        editCodigo = findViewById(R.id.editCodigo);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);

        popularSpinner();

        Produto produto = getIntent().getParcelableExtra("produto");
        if (produto != null){

            editNome.setText(produto.getNome());
            editCodigo.setText(produto.getCodigo());
            checkBox.setChecked(produto.getAtivado());
            int posicao = lista.indexOf(produto.getTipo());
            spinnerTipo.setSelection(posicao);
            radioGroup.check(produto.getRisco().ordinal());

        }
    }

    private void popularSpinner(){
        lista = new ArrayList<>();
        lista.add("Ações");
        lista.add("Funcos Imobiliarios");
        lista.add("CDB");
        lista.add("LCI/LCA");
        lista.add("Tesouro direto");
        lista.add("Criptomoedas");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinnerTipo.setAdapter(adapter);
    }

    public void limpar(){//View view){
        editNome.setText("");
        editCodigo.setText("");
        radioGroup.clearCheck();
        checkBox.setChecked(false);

        Toast.makeText(this, R.string.campos_reiniciados, Toast.LENGTH_SHORT).show();

        editNome.requestFocus();

    }

    public void salvar(){//(View view){
         if (editNome.getText().toString().isEmpty()){
             Toast.makeText(this, R.string.nome_vazio, Toast.LENGTH_SHORT).show();
             editNome.requestFocus();
             return;
         }

         if (editCodigo.getText().toString().isEmpty()) {
             Toast.makeText(this, R.string.codigo_vazio, Toast.LENGTH_SHORT).show();
             editCodigo.requestFocus();
             return;
         }

         int itemSelecionado = radioGroup.getCheckedRadioButtonId();

         if (itemSelecionado == -1){
             Toast.makeText(this, R.string.risco_produto, Toast.LENGTH_SHORT).show();
             return;
         } else {
             RadioButton riscoSelecionado = findViewById(itemSelecionado);
             if (riscoSelecionado.getText().equals("Baixo")) {
                 risco = ProdutoRisco.BAIXO;
             } else if(riscoSelecionado.getText().equals("Médio")) {
                 risco = ProdutoRisco.MEDIO;
             } else {
                 risco = ProdutoRisco.ALTO;
             }
         }

        TipoProduto tipo = new TipoProduto(spinnerTipo.getSelectedItem().toString());

        Produto produto = new Produto(editNome.getText().toString(),editCodigo.getText().toString(), risco, tipo, checkBox.isChecked());
        Intent intent = new Intent();
        intent.putExtra("produto", produto);

        Toast.makeText(this, R.string.sucesso, Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void cancelar(View view){
          setResult(Activity.RESULT_CANCELED);
          finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemSalvar){
            //ProdutoActivity.nova(this, laucherNovoProduto);
            salvar();
            return true;
        } else if (idMenuItem == R.id.menuItemLimpar){
            limpar(); //SobreActivity.nova(this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}