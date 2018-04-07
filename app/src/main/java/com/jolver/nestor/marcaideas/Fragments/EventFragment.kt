package com.jolver.nestor.marcaideas.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.jolver.nestor.marcaideas.Adapters.EventAdapter
import com.jolver.nestor.marcaideas.Models.Event

import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.EventService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass.
 */
class EventFragment : Fragment() {
    var viewRoot: View? = null
    var lvEventos: ListView? = null
    var retrofit: Retrofit? = null
    var adapter: EventAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewRoot = inflater!!.inflate(R.layout.fragment_event, container, false)



        if (context != null) {
            retrofit = myRetrofit;
            lvEventos = viewRoot!!.findViewById(R.id.lv_eventos)



            retrofit!!.create(EventService::class.java).listado().enqueue(object : Callback<List<Event>> {
                override fun onFailure(call: Call<List<Event>>?, t: Throwable?) {
                    Log.e("EVENTOS", t.toString())
                }

                override fun onResponse(call: Call<List<Event>>?, response: Response<List<Event>>?) {
                    if (response!!.code() == 200) {
                        adapter = EventAdapter(R.layout.item_evento, context, response?.body())
                        lvEventos!!.adapter = adapter
                    }
                }

            })


        }






        return viewRoot
    }

}
