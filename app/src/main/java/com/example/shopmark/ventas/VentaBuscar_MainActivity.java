package com.example.shopmark.ventas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopmark.Modelo.VentasModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.google.android.material.textfield.TextInputLayout;

public class VentaBuscar_MainActivity extends AppCompatActivity {
    TextInputLayout txCodigo, txFecha, txCodigoP, txCodigoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventabuscar_activity);
        enlazarControles();

        String bCodigo= "", bFecha="", bCodigoP="", bCodigoC="";
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            bCodigo=extras.getString("txtCodigo");
            bFecha=extras.getString("txFecha");
            bCodigoP=extras.getString("TxCodigoP");
            bCodigoC=extras.getString("TxCodigoC");

        }
        txCodigo.getEditText().setText(bCodigo);
        txFecha.getEditText().setText(bFecha);
        txCodigoP.getEditText().setText(bCodigoP);
        txCodigoC.getEditText().setText(bCodigoC);
        //extras

    }

    private void enlazarControles() {
        txCodigo = (TextInputLayout) findViewById(R.id.txCodigo);
        txFecha = (TextInputLayout) findViewById(R.id.txFecha);
        txCodigoP = (TextInputLayout) findViewById(R.id.txCodigoP);
        txCodigoC = (TextInputLayout) findViewById(R.id.txCodigoC);
    }

    public void procesar(View v) {
        if (v.getId() == R.id.btnBuscar1) {
            BuscarVENTA();
        } else if (v.getId() == R.id.btnEditar) {
            EditarVENTA();
        } else if (v.getId() == R.id.btnEliminar) {
            EliminarVEMTA();
        }
    }

    private void EditarVENTA() {
        if(validarEditar()) {
            final TiendaDB ventasDB = new TiendaDB(getApplicationContext());

            VentasModelo ventas = new VentasModelo();
            ventasDB.buscarVENTAS(ventas, txCodigo.getEditText().getText().toString());

            if (ventas.getCodigoProducto() == null) {
                Toast.makeText(getApplicationContext(), "VENTA NO ENCONTRADA", Toast.LENGTH_SHORT).show();
            }else {
                ventasDB.editarVENTAS(txCodigo.getEditText().getText().toString(),
                        txCodigoP.getEditText().getText().toString(),
                        txCodigoC.getEditText().getText().toString(),
                        txFecha.getEditText().getText().toString());

                Toast.makeText(getApplicationContext(), "VENTA MODIFICADA", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void EliminarVEMTA() {
        if(validarEliminarBuscar()) {
            final TiendaDB ventaDB = new TiendaDB(getApplicationContext());
            VentasModelo ventas = new VentasModelo();
            ventaDB.buscarVENTAS(ventas, txCodigo.getEditText().getText().toString());

            if (ventas.getCodigoProducto() == null) {
                Toast.makeText(getApplicationContext(), "CODIGO DE PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }else{
                ventaDB.eliminarVENTAS(txCodigo.getEditText().getText().toString());
                Toast.makeText(getApplicationContext(), "PRODUCTO ELIMINADO", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void BuscarVENTA() {
        if(validarEliminarBuscar()) {
            final TiendaDB ventaDB = new TiendaDB(getApplicationContext());
            VentasModelo ventas = new VentasModelo();

            ventaDB.buscarVENTAS(ventas, txCodigo.getEditText().getText().toString());
            txCodigoP.getEditText().setText(ventas.getCodigoProducto());
            txCodigoC.getEditText().setText(ventas.getCodigoCliente());
            txFecha.getEditText().setText(ventas.getFecha());

            if (ventas.getCodigoProducto() == null) {
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
        if(txCodigoP.getEditText().getText().toString().isEmpty()){
            txCodigoP.setError("Es necesario ingresar un CODIGO DE PRODUCTO");
            resp2=false;
        }
        if(txCodigoC.getEditText().getText().toString().isEmpty()){
            txCodigoC.setError("Es necesario ingresar un CODIGO DE CLIENTE");
            resp2=false;
        }
        if(txFecha.getEditText().getText().toString().isEmpty()){
            txFecha.setError("Es necesario ingresar una FECHA");
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