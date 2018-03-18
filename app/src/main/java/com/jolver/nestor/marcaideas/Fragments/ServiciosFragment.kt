package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import com.jolver.nestor.marcaideas.Activities.CategoriaActivity
import com.jolver.nestor.marcaideas.Adapters.GrupoAdapter
import com.jolver.nestor.marcaideas.Models.Grupo

import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.GrupoService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import com.jolver.nestor.marcaideas.Utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass.
 */
class ServiciosFragment : Fragment() {
    var v : View?=null
    var gv_grupo:GridView?=null
    var adapter:GrupoAdapter?=null
    var retrofit:Retrofit?=null
    var cProgress:ConstraintLayout?=null
    var cErrorConnection:ConstraintLayout?=null
    var btnreload:Button?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v=inflater!!.inflate(R.layout.fragment_servicios, container, false)
        return v


    }

    override fun onResume() {
        super.onResume()
        binding(v!!)
        btnreload!!.setOnClickListener({
            servicio()
        })
    }
    private fun binding(view:View) {
        gv_grupo=view.findViewById(R.id.gv_grupo)
        cProgress=view.findViewById(R.id.fs_CLProgressBar)
        cErrorConnection=view.findViewById(R.id.fs_CLErrorConnection)
        btnreload=view.findViewById(R.id.btnreload)

        servicio()


    }

    private fun servicio() {
        retrofit= myRetrofit
        val items=ArrayList<Grupo>()
        gv_grupo!!.visibility=View.GONE
        cProgress!!.visibility=View.VISIBLE
        cErrorConnection!!.visibility=View.GONE
        val services:GrupoService=retrofit!!.create(GrupoService::class.java)
        services.listadoGrupo().enqueue(object:Callback<List<Grupo>>{
            override fun onResponse(call: Call<List<Grupo>>?, response: Response<List<Grupo>>) {

                gv_grupo!!.visibility=View.VISIBLE
                cProgress!!.visibility=View.GONE
                cErrorConnection!!.visibility=View.GONE
                for(i in response.body()!!){
                    items.add(i)
                }
                if(context != null){
                    adapter= GrupoAdapter(R.layout.item_grupo,context,items,object:GrupoAdapter.OnClick{
                        override fun click(id: String) {
                            val intent = Intent(context, CategoriaActivity::class.java)
                            context.getSharedPreferences("marcaideas",Context.MODE_PRIVATE).edit().putString("id_grupo",id).apply()
                            startActivity(intent)
                        }
                    })

                    gv_grupo!!.adapter=adapter
                }

            }

            override fun onFailure(call: Call<List<Grupo>>?, t: Throwable?) {
                gv_grupo!!.visibility=View.GONE
                cProgress!!.visibility=View.GONE
                cErrorConnection!!.visibility=View.VISIBLE
            }
        })
    }

}
