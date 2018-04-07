package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.Categoria;
import com.jolver.nestor.marcaideas.Models.Imagenes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ImagenesService {
    @GET("imagenes/{id}")
    Call<List<Imagenes>> listado(@Path("id") String id);

}
