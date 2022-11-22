package com.example.shopmark.producto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;

public class ProductosActivity extends AppCompatActivity {
     private RecyclerView recyclerViewConsulta;
     private ProductosAdaptador adaptadorConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        recyclerViewConsulta =(RecyclerView)findViewById(R.id.recyclerConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        TiendaDB productoDB=new TiendaDB(getApplicationContext());

        adaptadorConsulta =new ProductosAdaptador(productoDB.mostrarProductos());
        recyclerViewConsulta.setAdapter(adaptadorConsulta);


    }


}