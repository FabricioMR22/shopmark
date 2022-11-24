package com.example.shopmark.producto;

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

import com.example.shopmark.Modelo.ProductoModelo;
import com.example.shopmark.R;
import com.example.shopmark.base.TiendaDB;

import java.util.List;

public class ProductosAdaptador extends RecyclerView.Adapter<ProductosAdaptador.ViewHolder>  {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        private TextView txtCodigo, txtProducto, txtStock, txtPrecio;
        Button btnmEditar, btnmEliminar;
        public ViewHolder(View itemView){
            super(itemView);

            context=itemView.getContext();
            txtCodigo=(TextView) itemView.findViewById(R.id.txtCodigo);
            txtProducto=(TextView) itemView.findViewById(R.id.txtProducto);
            txtStock=(TextView) itemView.findViewById(R.id.txtStock);
            txtPrecio=(TextView) itemView.findViewById(R.id.txtPrecio);


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
                    Intent intent=new Intent(context, PBuscasActivity.class);
                    intent.putExtra("txtCodigo",txtCodigo.getText());
                    intent.putExtra("txtProducto",txtProducto.getText());
                    intent.putExtra("txtStock",txtStock.getText());
                    intent.putExtra("txtPrecio",txtPrecio.getText());
                    context.startActivity(intent);
                    break;
                case R.id.btnmEliminar:
                    String codigoB="";
                    codigoB= txtCodigo.getText().toString();
                    if(codigoB.equals("")){

                    }else {
                        eliminarProducto(codigoB);
                    }
                    break;
            }
        }

        private void eliminarProducto(String codigoB) {
            final TiendaDB productoDB = new TiendaDB(context);
            productoDB.eliminarProductos(codigoB);
            Toast.makeText(context, "DATOS ELIMINADOS", Toast.LENGTH_SHORT).show();

            Intent mostrarProductos=new Intent(context, ProductosActivity.class);
            context.startActivity(mostrarProductos);
            ((Activity) context).finish();
        }
    }
    public List<ProductoModelo> productoLista;
    public ProductosAdaptador(List<ProductoModelo>productoLista){
        this.productoLista =productoLista;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCodigo.setText(productoLista.get(position).getCodigo());
        holder.txtProducto.setText(productoLista.get(position).getProducto());
        holder.txtStock.setText(""+productoLista.get(position).getStock());
        holder.txtPrecio.setText(""+productoLista.get(position).getPrecio());

        holder.setOnclickListeners();
    }



    @Override
    public int getItemCount() {
        return productoLista.size();
    }


}
