package com.jolver.nestor.marcaideas.Activities


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout


import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log

import android.widget.ListView
import com.jolver.nestor.marcaideas.Adapters.LugarAdapter
import com.jolver.nestor.marcaideas.Adapters.PagerAdapter
import com.jolver.nestor.marcaideas.Models.Lugar
import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.LugarService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import com.jolver.nestor.marcaideas.Utils.CreateCustomDialog
import com.jolver.nestor.marcaideas.Utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import com.jolver.nestor.marcaideas.R.id.tabLayout
import android.widget.Toast
import com.jolver.nestor.marcaideas.R.id.tabLayout






class LugaresActivity : AppCompatActivity() {
    var id_categoria: String? = null
    var lv: ListView? = null
    var sv: SearchView? = null
    var retrofit: Retrofit? = null
    var toolbar: Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)

        toolbar=findViewById(R.id.al_toolbar)
        setSupportActionBar(toolbar!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)





        id_categoria = getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("id_categoria", "")
        toolbar!!.title=getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("categoria", "")
        binding()

    }

    private fun binding() {
        lv = findViewById(R.id.lv_lugares)
        sv = findViewById(R.id.svLugares)




        retrofit = myRetrofit


        var service = retrofit!!.create(LugarService::class.java).listado(id_categoria)
        service.enqueue(object : Callback<MutableList<Lugar>> {
            override fun onResponse(call: Call<MutableList<Lugar>>?, response: Response<MutableList<Lugar>>?) {

                val adapter = LugarAdapter(R.layout.item_lugar, applicationContext, response!!.body(), object:LugarAdapter.OnClick{
                    override fun getLugar(lugar: Lugar) {
                        val editor=getSharedPreferences("marcaideas", Context.MODE_PRIVATE).edit()
                        editor.putString("lugar_id",lugar.id.toString())
                        editor.putString("lugar_razon_social",lugar.razon_social)
                        editor.putString("lugar_categoria_id",lugar.categoria_id.toString())
                        editor.putString("lugar_descripcion",lugar.descripcion)
                        editor.putString("lugar_image_url",lugar.image_url)
                        editor.putString("lugar_horario_apertura",lugar.horario_apertura)
                        editor.putString("lugar_horario_cierre",lugar.horario_cierre)
                        editor.putString("lugar_lat",lugar.lat.toString())
                        editor.putString("lugar_lon",lugar.lon.toString())
                        editor.putString("lugar_telefono",lugar.telefono)
                        editor.putString("lugar_direccion",lugar.direccion)
                        editor.putString("lugar_redes",lugar.redes)
                        editor.putString("lugar_ubicacion",lugar.ubicacion)
                        editor.putString("lugar_created_at",lugar.created_at)
                        editor.putString("lugar_updated_at",lugar.updated_at)
                        editor.putString("lugar_deleted_at",lugar.deleted_at)
                        editor.apply()


                        var intent=Intent(applicationContext,DetalleLugarActivity::class.java);
                        intent.putExtra("rs",lugar.razon_social)
                        startActivity(intent)
                    }
                })
                lv!!.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<Lugar>>?, t: Throwable?) {
                toast()
                Log.e("error retrofit", t.toString())
            }
        })


    }

}
