package com.example.shopmark.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.login_MainActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class register_MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText txtdni, txtNombre, txtApellido, txtCorreo, txtPassword;
    ImageView ivCodigoQR;
    Button btnGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        enlazarControles();
    }

    private void enlazarControles() {
        txtdni=(EditText) findViewById(R.id.txtdni);
        txtNombre=(EditText) findViewById(R.id.txtDNIi);
        txtApellido=(EditText) findViewById(R.id.txtApellido);
        txtCorreo=(EditText) findViewById(R.id.txtCorreo);
        txtPassword=(EditText) findViewById(R.id.txtPassword);

        ivCodigoQR = (ImageView) findViewById(R.id.ivCodigoQR);
        btnGenerar = (Button) findViewById(R.id.btnGenerar);

        btnGenerar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try{
            BarcodeEncoder barcoderEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcoderEncoder.encodeBitmap(
                    txtdni.getText().toString(), BarcodeFormat.QR_CODE,
                    700,700
            );
            ivCodigoQR.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void procesar(View v){
        if(v.getId()==R.id.btnRegistrar){
            generarCliente();
        }
    }

    private boolean validarOK() {
        boolean resp2=true;

        if(txtdni.getText().toString().isEmpty()){
            txtdni.setError("Es necesario ingresar un DNI");
            resp2=false;
        }
        if(txtNombre.getText().toString().isEmpty()){
            txtNombre.setError("Es necesario ingresar un nombre");
            resp2=false;
        }
        if(txtApellido.getText().toString().isEmpty()){
            txtApellido.setError("Es necesario ingresar una correo");
            resp2=false;

        }
        if(txtPassword.getText().toString().isEmpty()){
            txtPassword.setError("Es necesario ingresar un password");
            resp2=false;

        }


        return resp2;
    }


    private void generarCliente() {
        if(validarOK()) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            ClienteModelo clientes = new ClienteModelo();


            clienteDB.buscarCorreo(clientes, txtCorreo.getText().toString());

            if(clientes.getCorreo() == null){
                String cod = "hola";
                final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
                consultaBD.agregarClientes(cod.toString(), txtdni.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString(), txtCorreo.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "CORREO EXISTENTE", Toast.LENGTH_SHORT).show();

            }


        }
        }


    private void MostrarConsulta() {
        Intent mostrarConsultas=new Intent(getApplicationContext(), ClientesActivity.class);
        startActivity(mostrarConsultas);
    }

    public void goToLog(View view){
        Intent IrALogin = new Intent(this, login_MainActivity.class);
        startActivity(IrALogin);
    }



}