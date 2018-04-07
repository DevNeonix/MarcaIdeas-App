package com.jolver.nestor.marcaideas.Models

/**
 * Created by Andre on 7/02/2018.
 */
open class Oferta(var id: Int,
                  var lugar_id: Int,
                  var producto: String,
                  var descripcion: String,
                  var precio_regular: Double,
                  var precio_promocion: Double,
                  var descuento: Double,
                  var lat: Double,
                  var lon: Double,
                  var fecha_inicio: String,
                  var fecha_fin: String,
                  var image_url: String,
                  var condiciones: String)