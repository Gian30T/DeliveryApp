package com.example.deliveryapp.data.model;

public class RegistroRequest {
    public String nombre;
    public String apellido;
    public String correo;
    public String contraseña;
    public String telefono;

    public RegistroRequest(String nombre, String apellido, String correo, String contraseña, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }
}
