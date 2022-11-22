package com.example.shopmark.producto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.R;

public class menuProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_producto);
    }
    public void procesar(View v){
        if(v.getId()==R.id.btnNewProducto){
            PantallaNewConsulta();
        }else if(v.getId()==R.id.btnProductos){
            PantallaConsultas();
        }else if(v.getId()==R.id.btnBuscar){
            PantallaBuscar();
        }
    }

    private void PantallaConsultas() {

            Intent mostrarConsultas=new Intent(getApplicationContext(), ProductosActivity.class);
            startActivity(mostrarConsultas);

    }

    private void PantallaNewConsulta() {
        Intent NewConsulta=new Intent(menuProductoActivity.this, RegistroProducto.class);
        startActivity(NewConsulta);
    }

    private void PantallaBuscar() {
        Intent BuscarConsulta=new Intent(menuProductoActivity.this, PBuscasActivity.class);
        startActivity(BuscarConsulta);
    }
}