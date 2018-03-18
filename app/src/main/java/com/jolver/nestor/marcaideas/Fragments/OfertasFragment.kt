package com.jolver.nestor.marcaideas.Fragments


import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.jolver.nestor.marcaideas.Adapters.OfertaAdapters
import com.jolver.nestor.marcaideas.Models.Oferta

import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.OfertaService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import com.jolver.nestor.marcaideas.Utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass.
 */
class OfertasFragment : Fragment() {
    var rootView:View?=null
    var cProgress: ConstraintLayout?=null
    var cErrorConnection: ConstraintLayout?=null
    var btnreload: Button?=null
    var lv_ofertas: ListView?=null
    var retrofit:Retrofit?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView=inflater!!.inflate(R.layout.fragment_ofertas, container, false)


        return rootView!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lv_ofertas=rootView!!.findViewById(R.id.lv_ofertas)
        cProgress=rootView!!.findViewById(R.id.fo_CLProgressBar)
        cErrorConnection=rootView!!.findViewById(R.id.fo_CLErrorConnection)
        btnreload=rootView!!.findViewById(R.id.btnreload)
        retrofit= myRetrofit

        servicio()

    }

    private fun servicio() {
        lv_ofertas!!.visibility=View.GONE
        cProgress!!.visibility=View.VISIBLE
        cErrorConnection!!.visibility=View.GONE
        retrofit!!.create(OfertaService::class.java).listado().enqueue(object :Callback<List<Oferta>>{
            override fun onFailure(call: Call<List<Oferta>>?, t: Throwable?) {
                lv_ofertas!!.visibility=View.GONE
                cProgress!!.visibility=View.GONE
                cErrorConnection!!.visibility=View.VISIBLE

            }

            override fun onResponse(call: Call<List<Oferta>>?, response: Response<List<Oferta>>?) {
                lv_ofertas!!.visibility=View.VISIBLE
                cProgress!!.visibility=View.GONE
                cErrorConnection!!.visibility=View.GONE
                var items=ArrayList<Oferta>()
                for(i in response!!.body()!!){
                    items.add(i)
                }
                if(context != null){
                    var adapter=OfertaAdapters(items,context,R.layout.item_oferta,object:OfertaAdapters.OnClick{
                        override fun Listener(oferta: Oferta) {

                        }
                    })
                    lv_ofertas!!.adapter=adapter
                }
            }
        })

    }
}
