package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.Lugar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface LugarService {
    @GET("lugares/{id}")
    Call<List<Lugar>> listado(@Path("id") String id);
    @GET("lugar/{id}")
    Call<List<Lugar>> lugar(@Path("id") String id);
}
