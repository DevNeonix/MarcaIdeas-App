package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface CategoriaService {
    @GET("categorias/{id}")
    Call<List<Categoria>> listadoCategorias(@Path("id") String id);

}
