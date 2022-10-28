package com.example.proyecto_prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "BaseDeDatos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username Text primary key, password Text, nombre Text)");
        db.execSQL("create Table productos(codigo Integer primary key, nombrep Text, precio Integer, categoria Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop table if exists productos");
    }

    public Boolean insertData(String username, String password, String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("nombre",nombre);

        long result = db.insert("users", null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = ?", new String[] {username});

        if (cr.getCount()>0){
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkusernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});

        if (cr.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
/*
    public Boolean insertD(int codigo, String nombrep, int precio, String categoria){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", codigo);
        contentValues.put("nombrep", nombrep);
        contentValues.put("precio", precio);
        contentValues.put("categoria", categoria);
        long result = db.insert("productos", null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkcodigo(int codigo){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] arguments = new String[]{String.valueOf(codigo)};
        Cursor cr = db.rawQuery("select * from productos where codigo = ? ", arguments );

        if (cr.getCount()>0){
            return true;
        } else {
            return false;
        }
    }*/
}
