package com.example.shopmark.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.login_MainActivity;

public class register_MainActivity extends AppCompatActivity {
    EditText txtdni, txtNombre, txtApellido, txtCorreo, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        enlazarControles();

    }

    private void enlazarControles() {
        txtdni = (EditText) findViewById(R.id.txtdni);
        txtNombre = (EditText) findViewById(R.id.txtDNIi);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    public void procesar(View v) {
        if (v.getId() == R.id.btnRegistrar) {
            generarCliente();
        }
    }

    private boolean validarOK() {
        boolean resp2 = true;

        if (txtdni.getText().toString().isEmpty()) {
            txtdni.setError("Es necesario ingresar un DNI");
            resp2 = false;
        }
        if (txtNombre.getText().toString().isEmpty()) {
            txtNombre.setError("Es necesario ingresar un nombre");
            resp2 = false;
        }
        if (txtApellido.getText().toString().isEmpty()) {
            txtApellido.setError("Es necesario ingresar un apellido");
            resp2 = false;

        }
        if (txtCorreo.getText().toString().isEmpty()) {
            txtCorreo.setError("Es necesario ingresar un correo");
            resp2 = false;

        }
        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Es necesario ingresar un password");
            resp2 = false;

        }


        return resp2;
    }

    private void generarCliente() {
        if (validarOK()) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            ClienteModelo clientes = new ClienteModelo();


            clienteDB.buscarCorreo(clientes, txtCorreo.getText().toString());

            if (clientes.getCorreo() == null) {
                String cod = "c1";
                final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
                consultaBD.agregarClientes(cod.toString(), txtdni.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString(), txtCorreo.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "CORREO EXISTENTE", Toast.LENGTH_SHORT).show();

            }


        }
    }


}