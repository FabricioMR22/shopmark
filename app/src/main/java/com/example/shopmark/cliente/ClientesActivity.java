package com.example.shopmark.cliente;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;
import com.example.shopmark.producto.ProductosAdaptador;

public class ClientesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewConsulta;
    private ClientesAdaptador adaptadorConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        recyclerViewConsulta =(RecyclerView)findViewById(R.id.recyclerConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        TiendaDB consultaDB=new TiendaDB(getApplicationContext());

        adaptadorConsulta =new ClientesAdaptador(consultaDB.mostrarClientes());
        recyclerViewConsulta.setAdapter(adaptadorConsulta);



    }
    @Override
    public void onResume() {
        recyclerViewConsulta =(RecyclerView)findViewById(R.id.recyclerConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        TiendaDB consultaDB=new TiendaDB(getApplicationContext());

        adaptadorConsulta =new ClientesAdaptador(consultaDB.mostrarClientes());
        recyclerViewConsulta.setAdapter(adaptadorConsulta);
        super.onResume();

    }


}