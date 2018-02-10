package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jolver.nestor.marcaideas.R


/**
 * A simple [Fragment] subclass.
 */
class InformacionLugarFragment : Fragment() {
    var viewRoot:View?=null
    var tvRazonSocial: TextView?=null
    var tvDescripcion: TextView?=null
    var tvDireccion: TextView?=null
    var preferences: SharedPreferences?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        preferences=context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE)
        viewRoot=inflater!!.inflate(R.layout.fragment_informacion_lugar, container, false)
        return viewRoot!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvRazonSocial=viewRoot!!.findViewById(R.id.tvRazonSocial)
        tvDescripcion=viewRoot!!.findViewById(R.id.tvDescripcion)
        tvDireccion=viewRoot!!.findViewById(R.id.tvDireccion)

        tvRazonSocial!!.text=preferences!!.getString("lugar_razon_social","")
        tvDescripcion!!.text=preferences!!.getString("lugar_descripcion","")
        tvDireccion!!.text=preferences!!.getString("lugar_direccion","")
    }
}// Required empty public constructor
