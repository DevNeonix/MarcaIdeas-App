package com.jolver.nestor.marcaideas.Models

/**
 * Created by root on 21/01/18.
 */

open class User(var id: Int,
                var fullname: String,
                var email: String,
                var fecha_nacimiento: String,
                var remember_token: String,
                var created_at: String,
                var updated_at: String,
                var deleted_at: String,
                var url_image: String = "", var password: String)
