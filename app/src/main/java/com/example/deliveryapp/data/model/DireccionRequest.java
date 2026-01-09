package com.example.deliveryapp.data.model;

public class DireccionRequest {

    public int usuario_id;
    public String direccion;
    public String referencia;
    public String ciudad;
    public String distrito;

    public DireccionRequest(int usuario_id, String direccion, String referencia,
                            String ciudad, String distrito) {
        this.usuario_id = usuario_id;
        this.direccion = direccion;
        this.referencia = referencia;
        this.ciudad = ciudad;
        this.distrito = distrito;
    }
}
