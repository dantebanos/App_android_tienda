package com.example.proyecto_prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyecto_prueba.entidades.Productos;

import java.util.ArrayList;

public class DbProductos extends DBHelper{
    Context context;
    public DbProductos(@Nullable Context context) {

        super(context);
        this.context = context;
    }

    public boolean insertarProducto(String codigo, String nombrep, String precio, String categoria){

        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqldb = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("codigo",codigo);
        values.put("nombrep",nombrep);
        values.put("precio",precio);
        values.put("categoria",categoria);

        long result = sqldb.insert("productos",null, values);

        if (result == -1){
            return false;

        }else
        {
            return true;
        }

    }

    public ArrayList<Productos> mostrarProductos(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos producto = null;
        Cursor cr = null;

        cr = bd.rawQuery(" SELECT * FROM productos ", null);

        if (cr.moveToFirst()){
            do{
                producto = new Productos();
                producto.setCodigo(cr.getInt(0));
                producto.setNombre(cr.getString(1));
                producto.setCategoria(cr.getString(2));
                producto.setPrecio(cr.getInt(3));
                listaProductos.add(producto);

            }while (cr.moveToNext());
        }
        cr.close();

        return listaProductos;
    }

    public Productos verProductos(int id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        Productos producto = null;
        Cursor cr;

        cr = bd.rawQuery("select * from productos where codigo = "+ id + " LIMIT 1", null);
        if (cr.moveToFirst()){
            producto = new Productos();
            producto.setCodigo(cr.getInt(0));
            producto.setPrecio(cr.getInt(1));
            producto.setCategoria(cr.getString(2));
            producto.setNombre(cr.getString(3));

        }
        cr.close();

        return producto;
    }

    public boolean editarProducto(int codigo, String nombre, String categoria, int precio){

        boolean correct = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase bd = dbHelper.getWritableDatabase();

        try{
            bd.execSQL(" UPDATE productos SET nombrep = " + nombre + ", categoria =" + categoria + ", precio =" + precio + " WHERE codigo = " + codigo );
            correct = true;

        }catch (Exception ex){
            ex.toString();
            correct = false;
        }finally {
            bd.close();
        }
        return correct;
    }

}
