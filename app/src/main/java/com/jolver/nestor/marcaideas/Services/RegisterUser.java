package com.jolver.nestor.marcaideas.Services;

import com.jolver.nestor.marcaideas.Models.RUser;
import com.jolver.nestor.marcaideas.Models.RespuestaGenerica;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by root on 25/01/18.
 */

public interface RegisterUser {
    @POST("users")
    Call<RespuestaGenerica> register(@Body RUser user);
}
