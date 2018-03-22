package com.jolver.nestor.marcaideas.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//var myRetrofit = Retrofit.Builder().baseUrl("http://190.239.17.114/marcaideas/public/index.php/").addConverterFactory(GsonConverterFactory.create()).build()
var myRetrofit = Retrofit.Builder().baseUrl("http://192.168.1.37/").addConverterFactory(GsonConverterFactory.create()).build()