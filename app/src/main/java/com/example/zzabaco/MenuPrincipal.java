package com.example.zzabaco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    public void onClickAdminUsuarios(View view) {
        Intent myIntent = new Intent(MenuPrincipal.this, AdminUsuarios.class);
        startActivity(myIntent);
    }

    public void onClickCompras(View view) {
        Intent myIntent = new Intent(MenuPrincipal.this, Compras.class);
        startActivity(myIntent);
    }

    public void onClickInventario(View view) {
        Intent myIntent = new Intent(MenuPrincipal.this, Inventario.class);
        startActivity(myIntent);
    }

    public void onClickProveedores(View view) {
        Intent myIntent = new Intent(MenuPrincipal.this, Proveedores.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(MenuPrincipal.this, Inicio.class);
        startActivity(myIntent);
    }



}
