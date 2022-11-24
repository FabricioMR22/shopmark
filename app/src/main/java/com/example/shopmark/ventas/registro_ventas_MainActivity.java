package com.example.shopmark.ventas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;


public class registro_ventas_MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewConsulta;
    private VentasAdaptador adaptadorConsulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_ventas_activity);

        recyclerViewConsulta = (RecyclerView) findViewById(R.id.recyclerConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        TiendaDB ventasDB = new TiendaDB(getApplicationContext());
        adaptadorConsulta = new VentasAdaptador(ventasDB.mostrarVENTAS());
        recyclerViewConsulta.setAdapter(adaptadorConsulta);

    }

    @Override
    protected void onResume() {
        recyclerViewConsulta = (RecyclerView) findViewById(R.id.recyclerConsulta);
        recyclerViewConsulta.setLayoutManager(new LinearLayoutManager(this));

        TiendaDB ventasDB = new TiendaDB(getApplicationContext());

        adaptadorConsulta = new VentasAdaptador(ventasDB.mostrarVENTAS());
        recyclerViewConsulta.setAdapter(adaptadorConsulta);
        super.onResume();
    }
}