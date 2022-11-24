package com.example.shopmark.producto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.menu_MainActivity;
import com.example.shopmark.producto.ProductosActivity;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroProducto extends AppCompatActivity{
    TextInputLayout txtProducto, txtStock, txtPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroproducto);
        enlazarControles();

    }

    private void enlazarControles() {
        txtProducto=(TextInputLayout)findViewById(R.id.txtProducto);
        txtStock=(TextInputLayout)findViewById(R.id.txtStock);
        txtPrecio=(TextInputLayout) findViewById(R.id.txtCosto);
    }
    public void procesar(View v){
        if(v.getId()==R.id.btnGuardar){
            GenerarProducto();
        }else if(v.getId()==R.id.btnConsultas1){
            MostrarProducto();
        }
    }


    private boolean validarOK() {
        boolean resp2=true;

        if(txtProducto.getEditText().getText().toString().isEmpty()){
            txtProducto.setError("Es necesario ingresar un producto");
            resp2=false;
        }
        if(txtStock.getEditText().getText().toString().isEmpty()){
            txtStock.setError("Es necesario ingresar la cantidad del stock");
            resp2=false;

        }
        if(txtPrecio.getEditText().getText().toString().isEmpty()){
            txtPrecio.setError("Es necesario ingresar el precio");
            resp2=false;

        }
        return resp2;
    }


    private void GenerarProducto() {
        String producto="";
        int stock=Integer.parseInt(txtStock.getEditText().getText().toString());
        double precio=Double.parseDouble(txtPrecio.getEditText().getText().toString());
        if(validarOK()) {
            final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
            consultaBD.agregarProductos(producto.toString(), txtProducto.getEditText().getText().toString(), stock,precio);
            Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        }
    }

    private void MostrarProducto() {
        Intent mostrarProductos=new Intent(getApplicationContext(), ProductosActivity.class);
        startActivity(mostrarProductos);
    }




    public void retroceder(View view){
        Intent retroceder = new Intent(this, menuProductoActivity.class);
        startActivity(retroceder);
    }

}