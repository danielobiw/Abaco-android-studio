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

public class AdminUsuarios extends AppCompatActivity {

    private EditText dniUsu, nombreCompleto, emailUsu, rol, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios);

        dniUsu=(EditText) findViewById(R.id.dniAdminText);
        nombreCompleto = (EditText) findViewById(R.id.nombreUsuAdminText);
        emailUsu=(EditText) findViewById(R.id.emailUsuAdminText);
        rol=(EditText) findViewById(R.id.rolAdminText);
        contraseña=(EditText) findViewById(R.id.contraseñaAdminText);
    }

    public void RegistrarUsuario(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String dniAdminText = dniUsu.getText().toString();
        String nombreUsuAdminText = nombreCompleto.getText().toString();
        String emailUsuAdminText = emailUsu.getText().toString();
        String rolAdminText = rol.getText().toString();
        String contraseñaAdminText = contraseña.getText().toString();

        ContentValues registroUsu = new ContentValues();

        registroUsu.put("dniUsu", dniAdminText);
        registroUsu.put("nombreCompleto", nombreUsuAdminText);
        registroUsu.put("emailUsu", emailUsuAdminText);
        registroUsu.put("rol", rolAdminText);
        registroUsu.put("contraseña", contraseñaAdminText);

        db.insert("usuarios", null, registroUsu);
        db.close();

        dniUsu.setText("");
        nombreCompleto.setText("");
        emailUsu.setText("");
        rol.setText("");
        contraseña.setText("");

        Toast.makeText(this, "Los datos del usuario se cargaron correctamente", Toast.LENGTH_LONG).show();
    }

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

    public void EliminarUsuario(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docUsu = dniUsu.getText().toString();

        int cant = db.delete("usuarios", "dniUsu=" + docUsu, null);
        db.close();

        dniUsu.setText("");
        nombreCompleto.setText("");
        emailUsu.setText("");
        rol.setText("");
        contraseña.setText("");

        if(cant==1){
            Toast.makeText(this, "Se ha eliminado el usuario", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un usuario con ese dni", Toast.LENGTH_LONG).show();
        }
    }

    public void ActualizarProducto(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docUsu = dniUsu.getText().toString();
        String nombreUsuAdminText = nombreCompleto.getText().toString();
        String emailUsuText = emailUsu.getText().toString();
        String rolText = rol.getText().toString();
        String contraseñaText = contraseña.getText().toString();

        ContentValues registroUsu = new ContentValues();

        registroUsu.put("nombreCompleto", nombreUsuAdminText);
        registroUsu.put("emailUsu", emailUsuText);
        registroUsu.put("rol", rolText);
        registroUsu.put("contraseña", contraseñaText);

        int cant = db.update("usuarios", registroUsu, "dniUsu=" + docUsu, null);
        db.close();

        dniUsu.setText("");
        nombreCompleto.setText("");
        emailUsu.setText("");
        rol.setText("");
        contraseña.setText("");

        if(cant==1){
            Toast.makeText(this, "Se actualizaron los datos del usuario", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un usuario con ese id", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRegresar(View view) {
        Intent myIntent = new Intent(AdminUsuarios.this, MenuPrincipal.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(AdminUsuarios.this, Inicio.class);
        startActivity(myIntent);
    }

}