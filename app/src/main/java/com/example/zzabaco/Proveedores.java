package com.example.zzabaco;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Proveedores extends AppCompatActivity {

    private EditText nit, nombre, email, direccion, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        nit = (EditText) findViewById(R.id.nitText);
        nombre=(EditText) findViewById(R.id.nombreText);
        email=(EditText) findViewById(R.id.emailText);
        direccion=(EditText) findViewById(R.id.direccionText);
        telefono=(EditText) findViewById(R.id.telefonoText);
    }

    public void Insertar(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String nitText = nit.getText().toString();
        String nombreText = nombre.getText().toString();
        String emailText = email.getText().toString();
        String direccionText = direccion.getText().toString();
        String telefonoText = telefono.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nit", nitText);
        registro.put("nombre", nombreText);
        registro.put("email", emailText);
        registro.put("direccion", direccionText);
        registro.put("telefono", telefonoText);

        db.insert("proveedores", null, registro);
        db.close();

        nit.setText("");
        nombre.setText("");
        email.setText("");
        direccion.setText("");
        telefono.setText("");

        Toast.makeText(this, "Los datos del proveedor se cargaron correctamente", Toast.LENGTH_LONG).show();
    }

    public void Consultar(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String doc = nit.getText().toString();

        Cursor fila = db.rawQuery("SELECT nombre, email, direccion, telefono FROM proveedores WHERE nit=" + doc, null);

        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(0));
            email.setText(fila.getString(1));
            direccion.setText(fila.getString(2));
            telefono.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No existe un proveedor con ese nit", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public void Eliminar(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String doc = nit.getText().toString();

        int cant = db.delete("proveedores", "nit=" + doc, null);
        db.close();

        nit.setText("");
        nombre.setText("");
        email.setText("");
        direccion.setText("");
        telefono.setText("");

        if(cant==1){
            Toast.makeText(this, "Se ha eliminado el proveedor", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un proveedor con ese nit", Toast.LENGTH_LONG).show();
        }
    }

    public void Actualizar(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String doc = nit.getText().toString();
        String nombreText = nombre.getText().toString();
        String emailText = email.getText().toString();
        String direccionText = direccion.getText().toString();
        String telefonoText = telefono.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombreText);
        registro.put("email", emailText);
        registro.put("direccion", direccionText);
        registro.put("telefono", telefonoText);

        int cant = db.update("proveedores", registro, "nit=" + doc, null);
        db.close();

        nit.setText("");
        nombre.setText("");
        email.setText("");
        direccion.setText("");
        telefono.setText("");

        if(cant==1){
            Toast.makeText(this, "Se actualizaron los datos del proveedor", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un proveedor con ese nit", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRegresar(View view) {
        Intent myIntent = new Intent(Proveedores.this, MenuPrincipal.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(Proveedores.this, Inicio.class);
        startActivity(myIntent);
    }

}