package com.jolver.nestor.marcaideas.Adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import com.jolver.nestor.marcaideas.Models.Lugar
import com.jolver.nestor.marcaideas.Models.Oferta

/**
 * Created by Andre on 7/02/2018.
 */

class OfertaAdapters(internal var listado: List<Oferta>, internal var context: Context, internal var layout: Int, internal var onClick: OnClick) : BaseAdapter() {

    override fun getCount(): Int {
        return listado.size
    }

    override fun getItem(i: Int): Any {
        return listado[i]
    }

    override fun getItemId(i: Int): Long {
        return listado[i].id.toLong()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var view: View?=null
        var vh:ViewHolder?=null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        val oferta = listado[i]



        return view!!
    }

    interface OnClick {
        fun Listener(oferta: Oferta)
    }
}

private class ViewHolder(var view: View){



    init {

    }
}
