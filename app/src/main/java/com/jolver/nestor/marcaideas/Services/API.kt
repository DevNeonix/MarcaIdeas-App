package com.jolver.nestor.marcaideas.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//var myRetrofit = Retrofit.Builder().baseUrl("http://192.168.1.13/api/").addConverterFactory(GsonConverterFactory.create()).build()
var myRetrofit = Retrofit.Builder().baseUrl("http://gotoshopec.com/api.php/api/").addConverterFactory(GsonConverterFactory.create()).build()