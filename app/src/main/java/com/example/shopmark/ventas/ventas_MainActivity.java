package com.example.shopmark.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.Modelo.ProductoModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.menu_MainActivity;
import com.example.shopmark.producto.ProductosActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ventas_MainActivity extends AppCompatActivity {
    TextInputLayout txCodigo,txCodigoC,txCantidad;
    TextView txProducto,txPrecio,txStock,txNombre,txDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_main);

        enlazarControles();
        //agregado
        String bCodigo="",
                bProducto="",
                bStock="",
                bPrecio="";

        String bCodigoC="",
                bNombre="",
                bDNI="";

        Bundle extras=getIntent().getExtras();

        if(extras!=null){
            bCodigo=extras.getString("txtCodigo");
            bProducto=extras.getString("txtProducto");
            bStock=extras.getString("txtStock");
            bPrecio=extras.getString("txtPrecio");
        }

        if(extras!=null){
            bCodigoC=extras.getString("txtCodigoi");
            bDNI=extras.getString("txtDNIi");
            bNombre=extras.getString("txtNombrei");
        }

        txCodigoC.getEditText().setText(bCodigoC);
        txDNI.setText(bDNI);
        txNombre.setText(bNombre);

        txCodigo.getEditText().setText(bCodigo);
        txProducto.setText(bProducto);
        txStock.setText(bStock);
        txPrecio.setText(bPrecio);
    }

    private void enlazarControles() {
        txCodigo = (TextInputLayout) findViewById(R.id.txCodigo);
        txProducto = (TextView) findViewById(R.id.txProducto);
        txStock = (TextView) findViewById(R.id.txStock);
        txPrecio = (TextView) findViewById(R.id.txPrecio);

        txCodigoC = (TextInputLayout) findViewById(R.id.txCodigoC);
        txDNI = (TextView) findViewById(R.id.txDNI);
        txNombre = (TextView) findViewById(R.id.txNombre);

        txCantidad = (TextInputLayout) findViewById(R.id.txCantidad);
    }

    public void procesar(View v) {
        if (v.getId() == R.id.btnBuscar1) {
            BuscarProducto();
        } else if (v.getId() == R.id.btnBuscar2) {
            BuscarCliente();
        } else if (v.getId() == R.id.btnAgregar) {
            GenerarVenta();
        } else if (v.getId() == R.id.btnVentas) {
            MostrarVentas();
        }
    }

    private boolean validarOK() {
        boolean resp2=true;

        if(txCodigo.getEditText().getText().toString().isEmpty()){
            txCodigo.setError("Es necesario codigo de producto");
            resp2=false;
        }
        if(txCodigoC.getEditText().getText().toString().isEmpty()){
            txCodigoC.setError("Es necesario codigo de cliente");
            resp2=false;

        }
        if(txCantidad.getEditText().getText().toString().isEmpty()){
            txCantidad.setError("Es necesario ingresar la cantidad");
            resp2=false;

        }
        if(txProducto.getText().toString().isEmpty()){
            txProducto.setError("Es necesario que sea un producto existente");
            resp2=false;
        }
        if(txNombre.getText().toString().isEmpty()){
            txNombre.setError("Es necesario un cliente registrado");
            resp2=false;

        }

        return resp2;
    }

    private void GenerarVenta() {
        if(validarOK()) {
            String venta = "v1";
            DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, HH:mm:ss");
            String fecha = dateFormat.format(Calendar.getInstance().getTime());
            int cantidad = Integer.parseInt(txCantidad.getEditText().getText().toString());
            String codigoC = txCodigoC.getEditText().getText().toString();

            if (validarOK()) {
                final TiendaDB consultaBD = new TiendaDB(getApplicationContext());
                consultaBD.agregarVENTAS(venta,
                        txCodigo.getEditText().getText().toString(),txProducto.getText().toString()
                        ,codigoC,txNombre.getText().toString(), fecha,cantidad);
                Toast.makeText(getApplicationContext(), "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
            if(validarOK()){
                final TiendaDB productoDB = new TiendaDB(getApplicationContext());

                ProductoModelo productos = new ProductoModelo();
                productoDB.buscarProductos(productos, txCodigo.getEditText().getText().toString());

                if (productos.getProducto() == null) {
                    Toast.makeText(getApplicationContext(), "PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                }else {
                    Integer nuevoStock = Integer.parseInt(txStock.getText().toString()) - cantidad;
                    productoDB.editarProductos(txCodigo.getEditText().getText().toString(),
                            txProducto.getText().toString(),
                            nuevoStock,
                            Double.parseDouble(txPrecio.getText().toString()));
                    Toast.makeText(getApplicationContext(), "PRODUCTO RESTADO", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void BuscarProducto() {
        final TiendaDB productoDB = new TiendaDB(getApplicationContext());
        ProductoModelo productos = new ProductoModelo();
        if(validarEliminarBuscar(txCodigo)) {
            productoDB.buscarProductos(productos, txCodigo.getEditText().getText().toString());
            txProducto.setText(productos.getProducto());
            txStock.setText(""+productos.getStock());
            txPrecio.setText(""+productos.getPrecio());
            if (productos.getProducto() == null) {
                Toast.makeText(getApplicationContext(), "PRODUCTO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BuscarCliente() {
        if(validarEliminarBuscar(txCodigoC)) {
            final TiendaDB clienteDB = new TiendaDB(getApplicationContext());
            ClienteModelo clientes = new ClienteModelo();
            clienteDB.buscarClientes(clientes, txCodigoC.getEditText().getText().toString());
            txDNI.setText(clientes.getDni());
            txNombre.setText(clientes.getNombre());
            if (clientes.getNombre() == null) {
                Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validarEliminarBuscar(TextInputLayout Codigo) {
        boolean resp2=true;
        if(Codigo.getEditText().getText().toString().isEmpty()){
            Codigo.setError("Es necesario ingresar un código");
            resp2=false;
        }
        return resp2;
    }

    private void MostrarVentas() {
        Intent mostrarVenta=new Intent(getApplicationContext(), registro_ventas_MainActivity.class);
        startActivity(mostrarVenta);
        finish();
    }


}