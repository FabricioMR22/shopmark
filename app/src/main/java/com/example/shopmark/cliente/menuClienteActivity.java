package com.example.shopmark.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.R;

public class menuClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
    }
    public void procesar(View v){
         if(v.getId()==R.id.btnClientes){
            PantallaConsultas();
        }else if(v.getId()==R.id.btnBuscar){
            PantallaBuscar();
        }else if(v.getId()==R.id.btnNuevoCliente) {
             PantallaRegistrar();
         }
    }

    private void PantallaConsultas() {

            Intent mostrarConsultas=new Intent(getApplicationContext(), ClientesActivity.class);
            startActivity(mostrarConsultas);

    }



    private void PantallaBuscar() {
        Intent BuscarConsulta=new Intent(menuClienteActivity.this, CBuscasActivity.class);
        startActivity(BuscarConsulta);
    }


    private void PantallaRegistrar() {
        Intent RegistrarCliente=new Intent(menuClienteActivity.this, register_MainActivity.class);
        startActivity(RegistrarCliente);
    }
}