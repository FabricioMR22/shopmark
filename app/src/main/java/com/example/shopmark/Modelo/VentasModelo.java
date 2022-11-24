package com.example.shopmark.Modelo;

public class VentasModelo {
    private String codigo, codigoProducto,nombreProducto,codigoCliente,nombreCliente, fecha;
    private int cantidad;

    public VentasModelo(){
    }

    public VentasModelo(String codigo, String codigoProducto,String nombreProducto, String codigoCliente, String nombreCliente, String fecha, int cantidad) {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.cantidad = cantidad;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
