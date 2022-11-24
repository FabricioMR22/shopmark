package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.base.TiendaDB;

public class login_MainActivity extends AppCompatActivity {
    EditText txtUsuario,txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        enlazarControles();


    }

    private void enlazarControles() {
        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtPass=(EditText) findViewById(R.id.txtPass);

    }
    public void procesar(View v) {
        if (v.getId() == R.id.btnLogin) {
            BuscarUsuario();
        }
    }

    private void BuscarUsuario() {
        if(validarOK()) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            ClienteModelo clientes = new ClienteModelo();


            clienteDB.buscarUsuario(clientes, txtUsuario.getText().toString(),txtPass.getText().toString());
            if (clientes.getCodigo() == null) {
                Toast.makeText(getApplicationContext(), "USUARIO NO REGISTRADO", Toast.LENGTH_SHORT).show();
            }else {
                Intent pase=new Intent(getApplicationContext(), menu_MainActivity.class);
                startActivity(pase);
            }

        }
    }

    private boolean validarOK() {
        boolean resp2=true;

        if(txtUsuario.getText().toString().isEmpty()){
            txtUsuario.setError("Es necesario ingresar un usuario");
            resp2=false;
        }
        if(txtPass.getText().toString().isEmpty()){
            txtPass.setError("Es necesario ingresar un password");
            resp2=false;
        }

        return resp2;
    }

    //    public void goToMenu(View view){
//        Intent IrALogin = new Intent(this,menu_MainActivity.class);
//        startActivity(IrALogin);
//    }
    public void goToFirst(View view){
        Intent IrALogin = new Intent(this,first_MainActivity.class);
        startActivity(IrALogin);
    }




}