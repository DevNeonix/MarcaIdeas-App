package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.Lugar;
import com.jolver.nestor.marcaideas.Models.Oferta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface OfertaService {
    @GET("ofertas")
    Call<List<Oferta>> listado();
    @GET("ofertas/{id}")
    Call<List<Oferta>> filtrado(@Path("id") String id);
}
