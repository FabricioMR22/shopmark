package com.example.shopmark.producto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.menu_MainActivity;
import com.example.shopmark.producto.ProductosActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class RegistroProducto extends AppCompatActivity /*implements View.OnClickListener*/{
    TextInputLayout txtProducto, txtStock, txtPrecio;
    //ImageView ivCodigQR;
    //Button btnGenera;

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

       // ivCodigQR = (ImageView) findViewById(R.id.ivCodigoQR);
       // btnGenera = (Button) findViewById(R.id.btnGenerar);
       // btnGenera.setOnClickListener(this);
    }

   /* @Override
    public void onClick(View view) {
        try{
            BarcodeEncoder barcoderEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcoderEncoder.encodeBitmap(
                    txtProducto.getEditText().getText().toString().BarcodeFormat.QR_CODE,
                    300, 300
            );
            ivCodigQR.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
    
    
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
        if(validarOK()) {
            String producto = "p1";
            int stock = Integer.parseInt(txtStock.getEditText().getText().toString());
            double precio = Double.parseDouble(txtPrecio.getEditText().getText().toString());
            if (validarOK()) {
                final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
                consultaBD.agregarProductos(producto.toString(), txtProducto.getEditText().getText().toString(), stock, precio);
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void MostrarProducto() {
        Intent mostrarProductos=new Intent(getApplicationContext(), ProductosActivity.class);
        startActivity(mostrarProductos);
        finish();
    }
}