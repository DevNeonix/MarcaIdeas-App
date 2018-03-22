package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.Categoria;
import com.jolver.nestor.marcaideas.Models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface EventService {
    @GET("eventos")
    Call<List<Event>> listado();
}
