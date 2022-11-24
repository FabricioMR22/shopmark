package com.example.shopmark.Modelo;

public class VentasModelo {
    private String codigo, codigoProducto,codigoCliente, fecha;
    private int cantidad;

    public VentasModelo(String codigo, String codigoProducto, String codigoCliente, String fecha, int cantidad) {
        this.codigo = codigo;
        this.codigoProducto = codigoProducto;
        this.codigoCliente = codigoCliente;
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
}
