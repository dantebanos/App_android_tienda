package com.example.proyecto_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        cargarComponentes();
    }

    private void cargarComponentes() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registrar.class);
                startActivity(intent);

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Por favor, ingrese las credenciales", Toast.LENGTH_SHORT).show();
                } else
                {
                    Boolean result = myDB.checkusernamePassword(username,password);
                    if (result == true){
                        Intent in = new Intent(getApplicationContext(),Inicio.class);
                        in.putExtra("usr",username);

                        startActivity(in);

                    } else
                    {
                        Toast.makeText(MainActivity.this, " Credenciales incorrectas ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void inicializarComponentes() {
        user = findViewById(R.id.User);
        pass = findViewById(R.id.Pass);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        myDB = new DBHelper(this);


    }


}