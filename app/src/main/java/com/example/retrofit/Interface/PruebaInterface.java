package com.example.retrofit.Interface;

import com.example.retrofit.Model.Datos;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PruebaInterface {

    @GET("MagicFood/clientes/terminalPublico.php?actividad=777")
    Call<List<Datos>>getAnswer();
}
