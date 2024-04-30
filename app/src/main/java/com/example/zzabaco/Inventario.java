package com.example.zzabaco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /*public void onClickConsultarProducto(View view) {
        Intent myIntent = new Intent(Inventario.this, Compras.class);
        startActivity(myIntent);
    }*/

    public void onClickRegresar(View view) {
        Intent myIntent = new Intent(Inventario.this, MenuPrincipal.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(Inventario.this, Inicio.class);
        startActivity(myIntent);
    }

}