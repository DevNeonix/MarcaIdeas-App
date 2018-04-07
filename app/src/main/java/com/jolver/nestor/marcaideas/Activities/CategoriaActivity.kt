package com.jolver.nestor.marcaideas.Activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import com.jolver.nestor.marcaideas.Adapters.CategoriaAdapter
import com.jolver.nestor.marcaideas.Models.Categoria
import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.CategoriaService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import com.jolver.nestor.marcaideas.Utils.toast
import kotlinx.android.synthetic.main.activity_categoria.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CategoriaActivity : AppCompatActivity() {
    var lvcategoria: ListView? = null
    var sV_categoria: SearchView? = null
    var retrofit: Retrofit? = null
    var id: String? = null
    var cProgress: ConstraintLayout? = null
    var cErrorConnection: ConstraintLayout? = null
    var ac_lllistado: LinearLayout? = null
    var btnreload: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)


        var preferences = getSharedPreferences("marcaideas", Context.MODE_PRIVATE);
        id = preferences.getString("id_grupo", "")
        var nombre = preferences.getString("nombre_grupo", "categoria")
        ac_toolbar.title = nombre



        setSupportActionBar(ac_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)




        binding()
        miservicio()
        btnreload!!.setOnClickListener {
            miservicio()
        }

    }

    private fun miservicio() {
        cErrorConnection!!.visibility = View.GONE
        cProgress!!.visibility = View.VISIBLE
        ac_lllistado!!.visibility = View.GONE
        var servicio = retrofit!!.create(CategoriaService::class.java)
        servicio.listadoCategorias(id!!).enqueue(object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>?, response: Response<List<Categoria>>?) {
                cErrorConnection!!.visibility = View.GONE
                cProgress!!.visibility = View.GONE
                ac_lllistado!!.visibility = View.VISIBLE

                var adapter = CategoriaAdapter(R.layout.item_categoria, applicationContext, response!!.body(), object : CategoriaAdapter.HicisteClick {
                    override fun MeDevuelveElId(categoria: Categoria?) {

                        val intent = Intent(applicationContext, LugaresActivity::class.java)
                        var editor = getSharedPreferences("marcaideas", Context.MODE_PRIVATE).edit()
                        editor.putString("id_categoria", categoria!!.id.toString())
                        editor.putString("categoria", categoria!!.nombre)
                        editor.apply()
                        startActivity(intent)
                    }
                })
                lvcategoria!!.adapter = adapter
            }

            override fun onFailure(call: Call<List<Categoria>>?, t: Throwable?) {
                toast()
                cErrorConnection!!.visibility = View.VISIBLE
                cProgress!!.visibility = View.GONE
                ac_lllistado!!.visibility = View.GONE
            }
        })
    }

    private fun binding() {
        lvcategoria = findViewById(R.id.lv_categoria)
        sV_categoria = findViewById(R.id.sV_categoria)
        btnreload = findViewById(R.id.btnreload)


        ac_lllistado = findViewById(R.id.ac_lllistado)
        cErrorConnection = findViewById(R.id.ac_CLErrorConnection)
        cProgress = findViewById(R.id.ac_CLProgressBar)


        retrofit = myRetrofit
    }
}
