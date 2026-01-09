package com.example.deliveryapp.data.local;

import com.example.deliveryapp.data.model.CarritoItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoManager {

    private static List<CarritoItem> carrito = new ArrayList<>();

    public static void agregarProducto(CarritoItem item) {
        for (CarritoItem c : carrito) {
            if (c.getProductoId() == item.getProductoId()) {
                c.setCantidad(c.getCantidad() + item.getCantidad());
                return;
            }
        }
        carrito.add(item);
    }

    public static List<CarritoItem> obtenerCarrito() {
        return carrito;
    }

    public static double obtenerTotal() {
        double total = 0;
        for (CarritoItem c : carrito) {
            total += c.getSubtotal();
        }
        return total;
    }

    public static void limpiar() {
        carrito.clear();
    }

    public static boolean estaVacio() {
        return carrito.isEmpty();
    }

    public static void eliminarProducto(int productoId) {
        Iterator<CarritoItem> iterator = carrito.iterator();

        while (iterator.hasNext()) {
            CarritoItem c = iterator.next();
            if (c.getProductoId() == productoId) {
                iterator.remove();
                return;
            }
        }
    }

}
