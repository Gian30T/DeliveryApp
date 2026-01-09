package com.example.deliveryapp.data.model;

public class LoginRequest {
    public String correo;
    public String contraseña;

    public LoginRequest(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }
}

