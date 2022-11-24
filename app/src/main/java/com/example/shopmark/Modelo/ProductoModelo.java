package com.example.shopmark.Modelo;

public class ProductoModelo {
    private String codigo,producto;
    private int stock;
    private double precio;


    public ProductoModelo(){
    }

    public ProductoModelo(String codigo, String producto, int stock, double precio) {
        this.codigo = codigo;
        this.producto = producto;
        this.stock = stock;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
