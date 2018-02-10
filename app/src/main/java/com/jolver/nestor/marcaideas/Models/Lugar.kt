package com.jolver.nestor.marcaideas.Models

import java.io.Serializable

/**
 * Created by root on 30/01/18.
 */

class Lugar(
        var id: Int,
        var categoria_id: Int,
        var razon_social: String,
        var descripcion: String,
        var image_url: String,
        var horario_apertura: String,
        var horario_cierre: String,
        var lat: Double,
        var lon: Double,
        var telefono: String,
        var direccion: String,
        var redes: String,
        var ubicacion: String,
        var created_at: String,
        var updated_at: String,
        var deleted_at: String)
