package com.example.shopmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopmark.cliente.menuClienteActivity;
import com.example.shopmark.producto.menuProductoActivity;
import com.example.shopmark.ventas.VentaBuscar_MainActivity;
import com.example.shopmark.ventas.ventas_MainActivity;

public class menu_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
    }

    public void goToFirst(View view){
        Intent IrALogin = new Intent(this,first_MainActivity.class);
        startActivity(IrALogin);
    }

    public void goToBuy(View view){
        Intent IrALogin = new Intent(this, ventas_MainActivity.class);
        startActivity(IrALogin);
    }

    public void clientes(View view){
        Intent IrMenuCliente = new Intent(this, menuClienteActivity.class);
        startActivity(IrMenuCliente);
    }

    public void productos(View view){
        Intent IrMenuProducto = new Intent(this, menuProductoActivity.class);
        startActivity(IrMenuProducto);
    }

    public void PantallaBuscar(View view) {
        Intent BuscarConsulta=new Intent(this, VentaBuscar_MainActivity.class);
        startActivity(BuscarConsulta);
    }
}