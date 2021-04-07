package com.example.subidanotacristian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText texto_nombre,texto_contraseña;
    Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    instancias();
    acciones();

    }

    private void acciones() {
        btn_iniciar.setOnClickListener(this);
    }

    private void instancias() {

        texto_nombre = findViewById(R.id.nombre);
        texto_contraseña = findViewById(R.id.contraseña);
        btn_iniciar = findViewById(R.id.btn_pasar);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
                case R.id.btn_pasar:
                    if(texto_contraseña.getText().toString().toLowerCase().equals(" ") || texto_nombre.getText().toString().toLowerCase().equals(" ")) {
                        Toast.makeText(getApplicationContext(),"DATOS VACIOS",Toast.LENGTH_SHORT).show();
                    }else{
                        if (texto_contraseña.getText().toString().toLowerCase().equals("admin") && texto_nombre.getText().toString().toLowerCase().equals("admin")) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("usuario", String.valueOf(texto_nombre.getText()));
                            startActivity(intent);

                        } else if(texto_contraseña.getText().toString().toLowerCase().equals("gamer") && texto_nombre.getText().toString().toLowerCase().equals("jugador")) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("usuario", String.valueOf(texto_nombre.getText()));
                            startActivity(intent);
                        }
                    }

                break;

        }
    }
}