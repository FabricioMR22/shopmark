package com.example.shopmark.producto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.Modelo.ProductoModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.google.android.material.textfield.TextInputLayout;

public class PBuscasActivity extends AppCompatActivity{
    TextInputLayout txCodigo, txProducto, txStock, txPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarproducto);
        enlazarControles();

        //agregado
        String bCodigo="", bProducto="", bStock="", bPrecio="";
            Bundle extras=getIntent().getExtras();
            if(extras!=null){
                bCodigo=extras.getString("txtCodigo");
                bProducto=extras.getString("txtProducto");
                bStock=extras.getString("txtStock");
                bPrecio=extras.getString("txtPrecio");

            }
            txCodigo.getEditText().setText(bCodigo);
            txProducto.getEditText().setText(bProducto);
            txStock.getEditText().setText(bStock);
            txPrecio.getEditText().setText(bPrecio);
        //extras

    }

    private void enlazarControles() {
        txCodigo = (TextInputLayout) findViewById(R.id.txCodigo);
        txProducto = (TextInputLayout) findViewById(R.id.txProducto);
        txStock = (TextInputLayout) findViewById(R.id.txStock);
        txPrecio = (TextInputLayout) findViewById(R.id.txPrecio);

    }

    public void procesar(View v) {
        if (v.getId() == R.id.btnBuscar1) {
            BuscarProducto();
        } else if (v.getId() == R.id.btnEditar) {
            EditarProducto();
        } else if (v.getId() == R.id.btnEliminar) {
            EliminarProducto();
        }
    }
    private void EditarProducto() {
        if(validarEditar()) {
            final TiendaDB productoDB = new TiendaDB(getApplicationContext());

            ProductoModelo productos = new ProductoModelo();
            productoDB.buscarProductos(productos, txCodigo.getEditText().getText().toString());

            if (productos.getProducto() == null) {
                Toast.makeText(getApplicationContext(), "PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }else {
                productoDB.editarProductos(txCodigo.getEditText().getText().toString(), txProducto.getEditText().getText().toString(),
                        Integer.parseInt(txStock.getEditText().getText().toString()), Double.parseDouble(txPrecio.getEditText().getText().toString()));
                Toast.makeText(getApplicationContext(), "PRODUCTO MOFICADO", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void EliminarProducto() {
        if(validarEliminarBuscar()) {
            final TiendaDB productoDB = new TiendaDB(getApplicationContext());
            ProductoModelo productos = new ProductoModelo();
            productoDB.buscarProductos(productos, txCodigo.getEditText().getText().toString());

            if (productos.getProducto() == null) {
                Toast.makeText(getApplicationContext(), "PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }else{
                productoDB.eliminarProductos(txCodigo.getEditText().getText().toString());
                Toast.makeText(getApplicationContext(), "PRODUCTO ELIMINADO", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void BuscarProducto() {
        if(validarEliminarBuscar()) {
            final TiendaDB productoDB = new TiendaDB(getApplicationContext());
            ProductoModelo productos = new ProductoModelo();


            productoDB.buscarProductos(productos, txCodigo.getEditText().getText().toString());
            txProducto.getEditText().setText(productos.getProducto());
            txStock.getEditText().setText(""+productos.getStock());
            txPrecio.getEditText().setText(""+productos.getPrecio());

            if (productos.getProducto() == null) {
                Toast.makeText(getApplicationContext(), "PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private boolean validarEditar() {
        boolean resp2=true;

        if(txCodigo.getEditText().getText().toString().isEmpty()){
            txCodigo.setError("Es necesario ingresar un código");
            resp2=false;
        }
        if(txProducto.getEditText().getText().toString().isEmpty()){
            txProducto.setError("Es necesario ingresar un nombre");
            resp2=false;
        }
        if(txStock.getEditText().getText().toString().isEmpty()){
            txStock.setError("Es necesario ingresar una cantidad");
            resp2=false;
        }
        if(txPrecio.getEditText().getText().toString().isEmpty()){
            txPrecio.setError("Es necesario ingresar un precio");
            resp2=false;
        }

        return resp2;
    }

    private boolean validarEliminarBuscar() {
        boolean resp2=true;

        if(txCodigo.getEditText().getText().toString().isEmpty()){
            txCodigo.setError("Es necesario ingresar un código");
            resp2=false;
        }

        return resp2;
    }

}


