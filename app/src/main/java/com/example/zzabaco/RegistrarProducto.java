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

public class RegistrarProducto extends AppCompatActivity {

    private EditText idProducto, descripcion, marca, precioCompra, cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);

        idProducto = (EditText) findViewById(R.id.dniAdminText);
        descripcion=(EditText) findViewById(R.id.nombreUsuAdminText);
        marca=(EditText) findViewById(R.id.emailUsuAdminText);
        precioCompra=(EditText) findViewById(R.id.rolAdminText);
        cantidad=(EditText) findViewById(R.id.contraseñaAdminText);
    }

    public void InsertarProducto(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String idProductoText = idProducto.getText().toString();
        String descripcionText = descripcion.getText().toString();
        String marcaText = marca.getText().toString();
        String precioCompraText = precioCompra.getText().toString();
        String cantidadText = cantidad.getText().toString();

        ContentValues registroProd = new ContentValues();

        registroProd.put("idProducto", idProductoText);
        registroProd.put("descripcion", descripcionText);
        registroProd.put("marca", marcaText);
        registroProd.put("precioCompra", precioCompraText);
        registroProd.put("cantidad", cantidadText);

        db.insert("productos", null, registroProd);
        db.close();

        idProducto.setText("");
        descripcion.setText("");
        marca.setText("");
        precioCompra.setText("");
        cantidad.setText("");

        Toast.makeText(this, "Los datos del producto se cargaron correctamente", Toast.LENGTH_LONG).show();
    }

    public void ConsultarProducto(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docProd = idProducto.getText().toString();

        Cursor fila = db.rawQuery("SELECT descripcion, marca, precioCompra, cantidad FROM productos WHERE idProducto=" + docProd, null);

        if (fila.moveToFirst()) {
            descripcion.setText(fila.getString(0));
            marca.setText(fila.getString(1));
            precioCompra.setText(fila.getString(2));
            cantidad.setText(fila.getString(3));
        } else {
            Toast.makeText(this, "No existe un producto con ese id", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public void EliminarProducto(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docProd = idProducto.getText().toString();

        int cant = db.delete("productos", "idProducto=" + docProd, null);
        db.close();

        idProducto.setText("");
        descripcion.setText("");
        marca.setText("");
        precioCompra.setText("");
        cantidad.setText("");

        if(cant==1){
            Toast.makeText(this, "Se ha eliminado el producto", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un producto con ese id", Toast.LENGTH_LONG).show();
        }
    }

    public void ActualizarProducto(View view) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        SQLiteDatabase db = admin.getWritableDatabase();
        String docProd = idProducto.getText().toString();
        String descripcionText = descripcion.getText().toString();
        String marcaText = marca.getText().toString();
        String precioCompraText = precioCompra.getText().toString();
        String cantidadText = cantidad.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("descripcion", descripcionText);
        registro.put("marca", marcaText);
        registro.put("precioCompra", precioCompraText);
        registro.put("cantidad", cantidadText);

        int cant = db.update("productos", registro, "idProducto=" + docProd, null);
        db.close();

        idProducto.setText("");
        descripcion.setText("");
        marca.setText("");
        precioCompra.setText("");
        cantidad.setText("");

        if(cant==1){
            Toast.makeText(this, "Se actualizaron los datos del producto", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No existe un prodcuto con ese id", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRegresar(View view) {
        Intent myIntent = new Intent(RegistrarProducto.this, MenuPrincipal.class);
        startActivity(myIntent);
    }

    public void onClickSalir(View view) {
        Intent myIntent = new Intent(RegistrarProducto.this, Inicio.class);
        startActivity(myIntent);
    }

}