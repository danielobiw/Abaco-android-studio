package com.example.zzabaco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Compras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickHistorialCompras(View view) {
        Intent myIntent = new Intent(Compras.this, HistorialCompras.class);
        startActivity(myIntent);
    }

    public void onClickRegistrarProducto(View view) {
        Intent myIntent = new Intent(Compras.this, RegistrarProducto.class);
        startActivity(myIntent);
    }

    public void onClickRegresar(View view) {
        Intent myIntent = new Intent(Compras.this, MenuPrincipal.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(Compras.this, Inicio.class);
        startActivity(myIntent);
    }

}