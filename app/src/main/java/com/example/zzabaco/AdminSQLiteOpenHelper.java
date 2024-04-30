package com.example.zzabaco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE proveedores(nit text primary key, nombre text, email text, direccion text, telefono text)");
        db.execSQL("CREATE TABLE usuarios(dniUsu text primary key, nombreCompleto text, emailUsu text, rol text, contraseña text)");
        db.execSQL("CREATE TABLE productos(idProducto text primary key, descripcion text, marca text, precioCompra text, cantidad text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS proveedores");
        db.execSQL("CREATE TABLE proveedores(nit text primary key, nombre text, email text, direccion text, telefono text)");

        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("CREATE TABLE usuarios(dniUsu text primary key, nombreCompleto text, emailUsu text, rol text, contraseña text)");

        db.execSQL("DROP TABLE IF EXISTS productos");
        db.execSQL("CREATE TABLE productos(idProducto text primary key, descripcion text, marca text, precioCompra text, cantidad text)");

    }
}
