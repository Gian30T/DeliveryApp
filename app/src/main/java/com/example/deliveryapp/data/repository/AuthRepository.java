package com.example.deliveryapp.data.repository;

import com.example.deliveryapp.data.api.ApiClient;
import com.example.deliveryapp.data.api.AuthApi;
import com.example.deliveryapp.data.model.LoginRequest;
import com.example.deliveryapp.data.model.LoginResponse;
import com.example.deliveryapp.data.model.RegistroRequest;
import com.example.deliveryapp.data.model.SimpleResponse;

import retrofit2.Call;

public class AuthRepository {

    private AuthApi api;

    public AuthRepository() {
        api = ApiClient.getClient().create(AuthApi.class);
    }

    public Call<LoginResponse> login(String correo, String contraseña) {
        LoginRequest request = new LoginRequest(correo, contraseña);
        return api.login(request);
    }

    public Call<SimpleResponse> registrar(RegistroRequest request) {
        AuthApi api = ApiClient.getClient().create(AuthApi.class);
        return api.registrar(request);
    }
}
