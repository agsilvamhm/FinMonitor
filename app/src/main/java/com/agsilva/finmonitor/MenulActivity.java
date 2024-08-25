package com.agsilva.finmonitor;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.agsilva.finmonitor.view.ListaProdutoActivity;
import com.agsilva.finmonitor.view.ProdutoActivity;
import com.agsilva.finmonitor.view.SobreActivity;

public class MenulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void menuSobre(View view){
        SobreActivity.nova(this);
    }

    public void menuCadastro(View view){
        ProdutoActivity.nova(this);
    }

    public void menuListaProduto(View view){
        ListaProdutoActivity.nova(this);
    }

}