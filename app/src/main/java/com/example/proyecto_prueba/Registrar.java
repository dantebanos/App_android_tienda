package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    EditText us, pass, nom, repass;
    DBHelper myDB;
    Button btnEntrar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        inicializarComponentes();
        cargarComponentes();
    }

    private void inicializarComponentes() {
        us = findViewById(R.id.User);
        nom = findViewById(R.id.Usernombre);
        pass = findViewById(R.id.Pass);
        repass = findViewById(R.id.RePass);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        myDB = new DBHelper(this);


    }

    private void cargarComponentes() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = us.getText().toString();
                String contra = pass.getText().toString();
                String repcontra = repass.getText().toString();
                String nombre = nom.getText().toString();

                if (usuario.equals("") || contra.equals("") || repcontra.equals("") || nombre.equals("")) {
                    Toast.makeText(Registrar.this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    if (contra.equals(repcontra)) {

                        Boolean usercheckResult = myDB.checkusername(usuario);
                        if (usercheckResult == false) {
                            Boolean regResult = myDB.insertData(usuario, contra, nombre);
                            if (regResult == true) {
                                Toast.makeText(Registrar.this, "Registro completado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Registrar.this, "Registro fallido", Toast.LENGTH_SHORT);
                            }

                        } else {
                            Toast.makeText(Registrar.this, "Usuario ya existe \n Inicia Sesión", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(Registrar.this, "Password no coinciden", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}