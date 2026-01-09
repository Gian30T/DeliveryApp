package com.example.deliveryapp.data.model;

public class CarritoItem {

    private int productoId;
    private String nombre;
    private double precio;
    private int cantidad;
    private String imagenUrl;

    public CarritoItem(int productoId, String nombre, double precio, int cantidad, String imagenUrl) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagenUrl = imagenUrl;
    }

    public int getProductoId() {
        return productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return precio * cantidad;
    }
}
