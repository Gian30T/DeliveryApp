package com.example.deliveryapp.data.api;

import com.example.deliveryapp.data.model.DireccionRequest;
import com.example.deliveryapp.data.model.LoginRequest;
import com.example.deliveryapp.data.model.LoginResponse;
import com.example.deliveryapp.data.model.Producto;
import com.example.deliveryapp.data.model.RegistroRequest;
import com.example.deliveryapp.data.model.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/usuarios/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/usuarios/register")
    Call<SimpleResponse> registrar(@Body RegistroRequest request);

    @POST("api/direcciones")
    Call<SimpleResponse> registrarDireccion(@Body DireccionRequest request);

    @GET("api/productos")
    Call<List<Producto>> listarProductos();
}
