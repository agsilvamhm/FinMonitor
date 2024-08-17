package com.agsilva.finmonitor.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.agsilva.finmonitor.R;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

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
             radioGroup.requestFocus();
             return;
         }
        Toast.makeText(this, R.string.sucesso, Toast.LENGTH_SHORT).show();
    }
}