package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrarProductos extends AppCompatActivity {
    EditText nomProd, codProd, catProd, precProd;
    Button btnReg, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);

        inicializarComponentes();
        cargarComponentes();

    }

    private void cargarComponentes() {
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbProductos dbProductos = new DbProductos(registrarProductos.this);
                boolean id = dbProductos.insertarProducto(codProd.getText().toString(),nomProd.getText().toString(), precProd.getText().toString(), catProd.getText().toString());

                if (id == true)
                {
                    Toast.makeText(registrarProductos.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
                else
                {
                    Toast.makeText(registrarProductos.this, "Error al guardar el registro", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Inicio.class);
                startActivity(intent);
            }
        });

    }
    private void limpiar(){
        nomProd.setText("");
        codProd.setText("");
        catProd.setText("");
        precProd.setText("");
    }

    private void inicializarComponentes() {
        nomProd = findViewById(R.id.nomProduc);
        codProd = findViewById(R.id.codigoProduc);
        catProd = findViewById(R.id.catProduc);
        precProd = findViewById(R.id.precioProduc);
        btnReg = findViewById(R.id.btnRegistrar);
        btnCancel = findViewById(R.id.btnCancelar);


    }
}