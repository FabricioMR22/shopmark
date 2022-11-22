package com.example.shopmark.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.google.android.material.textfield.TextInputLayout;

public class CBuscasActivity extends AppCompatActivity {
    TextInputLayout txCodigo,txDNI, txNombre, txApellido, txCorreo, txPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarcliente);
        enlazarControles();

        //agregado
        String bCodigo="",bCodigo2="", bDNI="",bNombre="", bApellido="", bCorreo="",bPassword="";
            Bundle extras=getIntent().getExtras();
            if(extras!=null){
                bCodigo=extras.getString("txtCodigoi");
                bDNI=extras.getString("txtDNIi");
                bNombre=extras.getString("txtNombrei");
                bApellido=extras.getString("txtApellidoi");
                bCorreo=extras.getString("txtCorreoi");
                bPassword=extras.getString("txtPasswordi");
            }


            txCodigo.getEditText().setText(bCodigo);
            txDNI.getEditText().setText(bDNI);
            txNombre.getEditText().setText(bNombre);
            txApellido.getEditText().setText(bApellido);
            txCorreo.getEditText().setText(bCorreo);
            txPassword.getEditText().setText(bPassword);

    }


    private void enlazarControles() {
        txCodigo = (TextInputLayout) findViewById(R.id.txCodigo);
        txDNI = (TextInputLayout) findViewById(R.id.txDNI);
        txNombre = (TextInputLayout) findViewById(R.id.txProducto);
        txApellido = (TextInputLayout) findViewById(R.id.txApellido);
        txCorreo = (TextInputLayout) findViewById(R.id.txCorreo);
        txPassword = (TextInputLayout) findViewById(R.id.txPassword);

    }

    public void procesar(View v) {
        if (v.getId() == R.id.btnBuscar1) {
            BuscarCliente();
        } else if (v.getId() == R.id.btnEditar) {
            EditarCliente();
        } else if (v.getId() == R.id.btnEliminar) {
            EliminarCliente();
        }else if (v.getId() == R.id.btnnClientes) {
            MostrarCliente();
        }
    }
    private void EditarCliente() {
        if(validarOK()) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            clienteDB.editarClientes(txCodigo.getEditText().getText().toString(), txDNI.getEditText().getText().toString(),txNombre.getEditText().getText().toString(),
                    txApellido.getEditText().getText().toString(), txCorreo.getEditText().getText().toString(),
                    txPassword.getEditText().getText().toString());

            Toast.makeText(getApplicationContext(), "DATOS MOFICADOS", Toast.LENGTH_SHORT).show();
        }
    }
    private void EliminarCliente() {
        if(validarOK()) {
            final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
            consultaBD.eliminarClientes(txCodigo.getEditText().getText().toString());
            Toast.makeText(getApplicationContext(), "DATOS ELIMINADOS", Toast.LENGTH_SHORT).show();
        }
    }




    public void BuscarCliente() {
        if(validarOK()) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            ClienteModelo clientes = new ClienteModelo();


            clienteDB.buscarClientes(clientes, txCodigo.getEditText().getText().toString());
            txDNI.getEditText().setText(clientes.getDni());
            txNombre.getEditText().setText(clientes.getNombre());
            txApellido.getEditText().setText(clientes.getApellido());
            txCorreo.getEditText().setText(clientes.getCorreo());
            txPassword.getEditText().setText(clientes.getPassword());

            if (clientes.getNombre() == null) {
                Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void MostrarCliente() {
        Intent mostrarConsultas=new Intent(getApplicationContext(), ClientesActivity.class);
        startActivity(mostrarConsultas);
    }





    private boolean validarOK() {
        boolean resp2=true;

        if(txCodigo.getEditText().getText().toString().isEmpty()){
            txCodigo.setError("Es necesario ingresar un código");
            resp2=false;
        }

        return resp2;
    }

}


