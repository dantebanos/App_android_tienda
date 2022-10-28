package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

Button btnRegProd, btnvisualizarProd,btnEliminarProd;
TextView textView1;
EditText editText1;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicio);

    inicializaComponentes();
    cargarDatos();
}

private void cargarDatos() {
    btnRegProd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),registrarProductos.class);
            startActivity(intent);
        }
    });

        btnvisualizarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),visualizarProducto.class);
                startActivity(in);
            }
        });
    btnEliminarProd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getApplicationContext(),EliminarProducto.class);
            startActivity(in);
        }
    });

    Bundle bundle = this.getIntent().getExtras();
    String correo = bundle.getString("usr");


    DBHelper cnx = new DBHelper(this);
    SQLiteDatabase db = cnx.getReadableDatabase();
    String query = query = "select * from users where username = '"
            + correo
            + "';";
    Cursor cursor = db.rawQuery(query, null);
    cursor.moveToFirst();
    String bdNombre = cursor.getString(2);

    textView1.setText("Hola! "+bdNombre);








    }

    private void inicializaComponentes() {

        btnRegProd = findViewById(R.id.btnRegProd);
        btnvisualizarProd = findViewById(R.id.btnVisProd);
        btnEliminarProd = findViewById(R.id.btnDelProd);
        textView1 = findViewById(R.id.nombreUsuario);
        editText1 = findViewById(R.id.User);
    }

}