package com.example.shopmark.ventas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmark.Modelo.VentasModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;

import java.util.List;

public class VentasAdaptador extends RecyclerView.Adapter<VentasAdaptador.ViewHolder>   {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        private TextView txtCodigoV,
                txtCodigoP, txtNombreP, txtCodigoC,
                txtNombreC, txtFecha,txtCantidad;
        Button btnmEditar, btnmEliminar;

        public ViewHolder(View itemView){
            super(itemView);

            context=itemView.getContext();
            txtCodigoV = (TextView) itemView.findViewById(R.id.txtCodigoV);
            txtCodigoP = (TextView) itemView.findViewById(R.id.txtCodigoP);
            txtNombreP = (TextView) itemView.findViewById(R.id.txtNombreP);
            txtCodigoC = (TextView) itemView.findViewById(R.id.txtCodigoC);
            txtNombreC = (TextView) itemView.findViewById(R.id.txtNombreC);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtCantidad);

            btnmEditar=(Button) itemView.findViewById(R.id.btnmEditarV);
            btnmEliminar=(Button) itemView.findViewById(R.id.btnmEliminarV);
        }

        void setOnclickListeners(){
            btnmEditar.setOnClickListener(this);
            btnmEliminar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btnmEditarV:
                    Intent intent=new Intent(context, VentaBuscar_MainActivity.class);
                    intent.putExtra("txtCodigoV",txtCodigoV.getText());
                    intent.putExtra("txtCodigoP",txtCodigoP.getText());
                    //intent.putExtra("txtNombreP",txtNombreP.getText());
                    intent.putExtra("txtCodigoC",txtCodigoC.getText());
                    //intent.putExtra("txtNombreC",txtCodigoC.getText());
                    intent.putExtra("txtFecha",txtCodigoC.getText());
                    //intent.putExtra("txtCantidad",txtCodigoC.getText());
                    context.startActivity(intent);
                    break;
                case R.id.btnmEliminarV:
                    String codigoB="";
                    codigoB= txtCodigoV.getText().toString();
                    if(codigoB.equals("")){

                    }else {
                        eliminarVenta(codigoB);
                    }
                    break;
            }

        }

        private void eliminarVenta(String codigoB) {
            final TiendaDB ventasDB = new TiendaDB(context);
            ventasDB.eliminarVENTAS(codigoB);
            Toast.makeText(context, "VENTA ELIMINADA", Toast.LENGTH_SHORT).show();

            Intent mostrarVentas=new Intent(context, registro_ventas_MainActivity.class);
            context.startActivity(mostrarVentas);
            ((Activity) context).finish();

        }

    }
    public List<VentasModelo> ventasLista;
    public VentasAdaptador(List<VentasModelo>ventasLista){
        this.ventasLista =ventasLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(
                parent.getContext()).inflate(
                        R.layout.item_ventas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCodigoV.setText(ventasLista.get(position).getCodigo());
        holder.txtCodigoP.setText(ventasLista.get(position).getCodigoProducto());
        holder.txtNombreP.setText(ventasLista.get(position).getNombreProducto());
        holder.txtCodigoC.setText(ventasLista.get(position).getCodigoCliente());
        holder.txtNombreC.setText(ventasLista.get(position).getNombreCliente());
        holder.txtFecha.setText(ventasLista.get(position).getFecha());
        holder.txtCantidad.setText(""+ventasLista.get(position).getCantidad());

        holder.setOnclickListeners();
    }

    @Override
    public int getItemCount() {
        return ventasLista.size();
    }
}
