package com.example.shopmark.cliente;

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

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;

import java.util.List;

public class ClientesAdaptador extends RecyclerView.Adapter<ClientesAdaptador.ViewHolder>  {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        private TextView txtCodigoi, txtDNIi, txtNombrei, txtApellidoi, txtCorreoi, txtPasswordi;
        Button btnmEditar, btnmEliminar;
        public ViewHolder(View itemView){
            super(itemView);

            context=itemView.getContext();
            txtCodigoi=(TextView) itemView.findViewById(R.id.txtCodigoi);
            txtDNIi=(TextView) itemView.findViewById(R.id.txtDNIi);
            txtNombrei=(TextView) itemView.findViewById(R.id.txtNombrei);
            txtApellidoi=(TextView) itemView.findViewById(R.id.txtApellidoi);
            txtCorreoi=(TextView) itemView.findViewById(R.id.txtCorreoi);
            txtPasswordi=(TextView) itemView.findViewById(R.id.txtPasswordi);

            btnmEditar=(Button) itemView.findViewById(R.id.btnmEditar);
            btnmEliminar=(Button) itemView.findViewById(R.id.btnmEliminar);
        }
        void setOnclickListeners(){
            btnmEditar.setOnClickListener(this);
            btnmEliminar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.btnmEditar:
                    Intent intent=new Intent(context, CBuscasActivity.class);
                    intent.putExtra("txtCodigoi",txtCodigoi.getText());
                    intent.putExtra("txtDNIi",txtDNIi.getText());
                    intent.putExtra("txtNombrei",txtNombrei.getText());
                    intent.putExtra("txtApellidoi",txtApellidoi.getText());
                    intent.putExtra("txtCorreoi",txtCorreoi.getText());
                    intent.putExtra("txtPasswordi",txtPasswordi.getText());
                    context.startActivity(intent);
                    break;
                case R.id.btnmEliminar:
                    String codigoB="";
                    codigoB= txtCodigoi.getText().toString();
                    if(codigoB.equals("")){

                    }else {
                        eliminarCliente2(codigoB);
                    }
                    break;
            }
        }

        private void eliminarCliente2(String codigo) {
            final TiendaDB consultaBD = new TiendaDB(context);
            consultaBD.eliminarClientes(codigo);
            Toast.makeText(context, "DATOS ELIMINADOS", Toast.LENGTH_SHORT).show();
            Intent mostrarConsultas=new Intent(context, ClientesActivity.class);
            context.startActivity(mostrarConsultas);
            ((Activity) context).finish();
        }
    }
    public List<ClienteModelo> clienteLista;
    public ClientesAdaptador(List<ClienteModelo>clienteLista){
        this.clienteLista =clienteLista;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCodigoi.setText(clienteLista.get(position).getCodigo());
        holder.txtDNIi.setText(clienteLista.get(position).getDni());
        holder.txtNombrei.setText(clienteLista.get(position).getNombre());
        holder.txtApellidoi.setText(clienteLista.get(position).getApellido());
        holder.txtCorreoi.setText(clienteLista.get(position).getCorreo());
        holder.txtPasswordi.setText(""+ clienteLista.get(position).getPassword());

        //agregado
        holder.setOnclickListeners();



    }



    @Override
    public int getItemCount() {
        return clienteLista.size();
    }


}
