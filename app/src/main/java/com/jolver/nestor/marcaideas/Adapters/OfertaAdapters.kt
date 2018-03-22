package com.jolver.nestor.marcaideas.Adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.jolver.nestor.marcaideas.Models.Lugar
import com.jolver.nestor.marcaideas.Models.Oferta
import com.jolver.nestor.marcaideas.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

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
        var view: View? = null
        var vh: ViewHolder? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }
        val oferta = listado[i]

        val preferences = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE)
        if (preferences.getString("id", "").equals("")) {
            vh.tvnombre!!.text = "Registrate para mirar nuestras ofertas."
            vh.io_oferta_antes!!.text = ""
            vh.io_oferta_ahora!!.text = ""
            vh.io_oferta_porcentaje!!.text = ""
            Picasso.with(context)
                    .load(R.drawable.logo)
                    .resize(150, 150)
                    .centerCrop()
                    .into(vh.img!!)
        } else {


            vh.tvnombre!!.text = oferta.producto
            vh.io_oferta_antes!!.text = "Antes: " + oferta.precio_regular.toString()
            vh.io_oferta_ahora!!.text = "Ahora!: " + oferta.precio_promocion.toString()
            vh.io_oferta_porcentaje!!.text = oferta.descuento.toString() + "%"
            Picasso.with(context)
                    .load(oferta.image_url)
                    .resize(150, 150)
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .into(vh.img!!)
        }




        return view!!
    }

    interface OnClick {
        fun Listener(oferta: Oferta)
    }
}

private class ViewHolder(var row: View) {
    var tvnombre: TextView? = null
    var io_oferta_antes: TextView? = null
    var io_oferta_ahora: TextView? = null
    var io_oferta_porcentaje: TextView? = null
    var img: ImageView? = null

    init {
        this.tvnombre = row?.findViewById(R.id.io_oferta_producto)
        this.io_oferta_antes = row?.findViewById(R.id.io_oferta_antes)
        this.io_oferta_ahora = row?.findViewById(R.id.io_oferta_ahora)
        this.io_oferta_porcentaje = row?.findViewById(R.id.io_oferta_porcentaje)
        this.img = row?.findViewById(R.id.item_oferta_iv)
    }
}
