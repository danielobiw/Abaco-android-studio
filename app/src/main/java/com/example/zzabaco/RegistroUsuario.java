package com.example.zzabaco;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroUsuario extends AppCompatActivity {

    public void onClickCancelarRegistro(View view) {
        Intent myIntent = new Intent(RegistroUsuario.this, Inicio.class);
        startActivity(myIntent);
    }

    private EditText dniUsu, nombreCompleto, emailUsu, rol, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        dniUsu=(EditText) findViewById(R.id.dniText);
        nombreCompleto = (EditText) findViewById(R.id.nombreUsuText);
        emailUsu=(EditText) findViewById(R.id.emailUsuText);
        rol=(EditText) findViewById(R.id.rolText);
        contraseña=(EditText) findViewById(R.id.contraseñaText);
    }

    public void RegistrarUsuario(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String dniText = dniUsu.getText().toString();
        String nombreUsuText = nombreCompleto.getText().toString();
        String emailUsuText = emailUsu.getText().toString();
        String rolText = rol.getText().toString();
        String contraseñaText = contraseña.getText().toString();

        ContentValues registroUsu = new ContentValues();

        registroUsu.put("dniUsu", dniText);
        registroUsu.put("nombreCompleto", nombreUsuText);
        registroUsu.put("emailUsu", emailUsuText);
        registroUsu.put("rol", rolText);
        registroUsu.put("contraseña", contraseñaText);

        db.insert("usuarios", null, registroUsu);
        db.close();

        dniUsu.setText("");
        nombreCompleto.setText("");
        emailUsu.setText("");
        rol.setText("");
        contraseña.setText("");

        Toast.makeText(this, "Los datos del usuario se cargaron correctamente", Toast.LENGTH_LONG).show();
    }


    //ESTE BOTON ES PROVICIONAL PARA CONSULTAR Y COMPROBAR SI FUNCIONA EL REGISTRO, PERO EL BOTON NO FUNCIONA.
    public void ConsultarUsuario(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docUsu = dniUsu.getText().toString();

        Cursor fila = db.rawQuery("SELECT nombreCompleto, emailUsu, rol, contraseña FROM usuarios WHERE dniUsu=" + docUsu, null);

        if (fila.moveToFirst()) {
            nombreCompleto.setText(fila.getString(0));
            emailUsu.setText(fila.getString(1));
            rol.setText(fila.getString(2));
            contraseña.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No existe un usuario con ese dni", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

}