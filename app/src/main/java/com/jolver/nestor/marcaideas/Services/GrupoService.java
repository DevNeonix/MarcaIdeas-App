package com.jolver.nestor.marcaideas.Services;



import com.jolver.nestor.marcaideas.Models.Grupo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 31/01/18.
 */

public interface GrupoService {
    @GET("grupos")
    Call<List<Grupo>> listadoGrupo();
}
