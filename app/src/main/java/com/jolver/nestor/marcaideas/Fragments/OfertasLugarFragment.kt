package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import com.jolver.nestor.marcaideas.Adapters.OfertaAdapters
import com.jolver.nestor.marcaideas.Models.Oferta

import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.OfertaService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass.
 */
class OfertasLugarFragment : Fragment() {

    var rootView: View? = null
    var lv_ofertas: ListView? = null
    var retrofit: Retrofit? = null
    var preferences: SharedPreferences?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        preferences=context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE)

        rootView = inflater!!.inflate(R.layout.fragment_ofertas_lugar, container, false)
        return rootView!!

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lv_ofertas = rootView!!.findViewById(R.id.ol_lv)
        retrofit = myRetrofit
        servicio()

    }

    private fun servicio() {
        retrofit!!.create(OfertaService::class.java).filtrado(preferences!!.getString("lugar_id","1")).enqueue(object : Callback<List<Oferta>> {
            override fun onFailure(call: Call<List<Oferta>>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<List<Oferta>>?, response: Response<List<Oferta>>?) {

                if (response!!.code() == 200) {
                    var items = ArrayList<Oferta>()
                    for (i in response!!.body()!!) {
                        items.add(i)
                    }
                    if (context != null) {
                        var adapter = OfertaAdapters(items, context, R.layout.item_oferta, object : OfertaAdapters.OnClick {
                            override fun Listener(oferta: Oferta) {

                            }
                        })
                        lv_ofertas!!.adapter = adapter
                    }
                }
            }
        })

    }

}
