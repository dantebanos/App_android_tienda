package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EliminarProducto extends AppCompatActivity {
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_producto2);
        inicializarComponentes();
        cargarDatos();
    }

    private void cargarDatos() {
        DBHelper cnx = new DBHelper(this);
        SQLiteDatabase db = cnx.getReadableDatabase();
        String query = "select * from productos;";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> datos = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String reg = cursor.getString(1);
                datos.add(reg);


            } while (cursor.moveToNext());
            db.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, datos);
            spinner1.setAdapter(adapter);

        }


    }




    private void inicializarComponentes(){
        spinner1 = findViewById(R.id.spinneron);

    }

    public void btn_delete(View view) {
        String eliminar = spinner1.getSelectedItem().toString();

        String query = "DELETE from productos where nombrep ='"
                + eliminar
                + "'";


        DBHelper cnx = new DBHelper(this);
        SQLiteDatabase db = cnx.getWritableDatabase();
        db.execSQL(query);
        cargarDatos();

        db.close();



        }


    }

