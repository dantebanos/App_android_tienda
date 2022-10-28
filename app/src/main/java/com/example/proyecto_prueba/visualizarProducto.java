package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto_prueba.entidades.Productos;

import java.util.ArrayList;

public class visualizarProducto extends AppCompatActivity {
 private ListView listView1;
 private EditText editText1;
 private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_producto);

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
                String reg = cursor.getString(0) + "|" +" "+
                        cursor.getString(1) + "|" + " "+
                        cursor.getString(2) + "|" + " "+
                        cursor.getString(3);
                datos.add(reg);


            } while (cursor.moveToNext());
            db.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, datos);
            listView1.setAdapter(adapter);

        }


    }

    private void inicializarComponentes() {
listView1 = findViewById(R.id.listview1);
editText1 = findViewById(R.id.textView10);
button2 = findViewById(R.id.button2);

    }


    public void btn_click1(View view) {

        busqueda(editText1.getText().toString());



    }

    private void busqueda(String busqueda) {
        String query= "";
        DBHelper cnx = new DBHelper(this);
        SQLiteDatabase db = cnx.getReadableDatabase();
        if(busqueda.equals("")){

             query = "select * from productos;";
        }
        else {
             query = "select * from productos where categoria = '"
                    + busqueda
                    + "';";
        }
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> datos = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String reg = cursor.getString(0) + "|" +" "+
                        cursor.getString(1) + "|" + " "+
                        cursor.getString(2) + "|" + " "+
                        cursor.getString(3);
                datos.add(reg);


            } while (cursor.moveToNext());
            db.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, datos);
            listView1.setAdapter(adapter);



        }


    }
}

