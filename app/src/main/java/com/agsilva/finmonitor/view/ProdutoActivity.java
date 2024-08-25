package com.agsilva.finmonitor.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
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
   //     activity.startActivity(intent);
    //    Intent intent = new Intent(this, ProdutoActivity.class);
        laucher.launch(intent);
    }

    public static final String NOME = "NOME";
    public static final String CODIGO = "CODIGO";
  //  public static final ProdutoRisco RISCO = ProdutoRisco.valueOf("RISCO");
  //  public static final String TIPO = "TIPO";
  //  public static final Boolean ATIVO = Boolean.valueOf("ATIVO");
   // public Produto = new Produto();

    private Spinner spinnerTipo;
    private EditText editNome, editCodigo;
    private RadioGroup radioGroup;
    private CheckBox checkBox;

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
    }

    private void popularSpinner(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Ações");
        lista.add("Funcos Imobiliarios");
        lista.add("CDB");
        lista.add("LCI/LCA");
        lista.add("Tesouro direto");
        lista.add("Criptomoedas");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

        spinnerTipo.setAdapter(adapter);
    }

    public void limpar(View view){
        editNome.setText("");
        editCodigo.setText("");
        radioGroup.clearCheck();
        checkBox.setChecked(false);

        Toast.makeText(this, R.string.campos_reiniciados, Toast.LENGTH_SHORT).show();

        editNome.requestFocus();

    }

    public void salvar(View view){
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

         if (radioGroup.getCheckedRadioButtonId() == -1){
             Toast.makeText(this, R.string.risco_produto, Toast.LENGTH_SHORT).show();
             //radioGroup.requestFocus(1);
             return;
         }

        int selectedId = radioGroup.getCheckedRadioButtonId();
        ProdutoRisco risco;
        TipoProduto tipo;
        tipo = new TipoProduto(spinnerTipo.getSelectedItem().toString());

        switch (selectedId) {
            case 1:
                risco = ProdutoRisco.BAIXO;
                break;
            case 2:
                risco = ProdutoRisco.MEDIO;
                break;
            default:
                // Definir um valor padrão ou lançar uma exceção
                risco = ProdutoRisco.ALTO;
                break;

        }

        Produto produto = new Produto(editNome.getText().toString(),editCodigo.getText().toString(), risco, tipo, checkBox.isChecked());
        Intent intent = new Intent();
  //      intent.putExtra("produto", produto);
        intent.putExtra(NOME, editNome.getText().toString());
        intent.putExtra(CODIGO, editCodigo.getText().toString());
 //       intent.putExtra(String.valueOf(RISCO), risco);
 //       intent.putExtra(TIPO, tipo.toString());
 //       intent.putExtra(String.valueOf(ATIVO), checkBox.isChecked());

        Toast.makeText(this, R.string.sucesso, Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void cancelar(View view){
          setResult(Activity.RESULT_CANCELED);
          finish();
    }
}