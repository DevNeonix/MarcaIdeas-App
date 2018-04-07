package com.jolver.nestor.marcaideas.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jolver.nestor.marcaideas.Activities.DetalleOfertaActivity

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
            vh.io_oferta_antes!!.text = "Antes: " + String.format("%.2f", oferta.precio_regular)
            vh.io_oferta_ahora!!.text = "Ahora!: " + String.format("%.2f", oferta.precio_promocion)
            vh.io_oferta_porcentaje!!.text = "Ahorras hasta un "+oferta.descuento.toString() + "%"


            val lat = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lat", "")
            val lon = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lon", "")
            val loc1 = Location("")
            loc1.latitude = oferta.lat
            loc1.longitude = oferta.lon

            val loc2 = Location("")
            loc2.latitude = java.lang.Double.parseDouble(lat)
            loc2.longitude = java.lang.Double.parseDouble(lon)

            val distanceInMeters = (loc2.distanceTo(loc1) / 1000).toDouble()
            vh.io_distancia!!.text = String.format("%.2f", distanceInMeters) + " km"



            Picasso.with(context)
                    .load(oferta.image_url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .into(vh.img!!)
            vh.img!!.setOnClickListener {
                var intent=Intent(context, DetalleOfertaActivity::class.java)
                intent.putExtra("oferta__id",oferta.id)
                intent.putExtra("oferta__lugar_id",oferta.lugar_id)
                intent.putExtra("oferta__producto",oferta.producto)
                intent.putExtra("oferta__descripcion",oferta.descripcion)
                intent.putExtra("oferta__precio_regular",oferta.precio_regular)
                intent.putExtra("oferta__precio_promocion",oferta.precio_promocion)
                intent.putExtra("oferta__descuento",oferta.descuento)
                intent.putExtra("oferta__lat",oferta.lat)
                intent.putExtra("oferta__lon",oferta.lon)
                intent.putExtra("oferta__fecha_inicio",oferta.fecha_inicio)
                intent.putExtra("oferta__fecha_fin",oferta.fecha_fin)
                intent.putExtra("oferta__image_url",oferta.image_url)
                intent.putExtra("oferta__condiciones",oferta.condiciones)
                context.startActivity(intent)
            }
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
    var io_distancia: TextView? = null
    var img: ImageView? = null

    init {
        this.tvnombre = row?.findViewById(R.id.io_oferta_producto)
        this.io_oferta_antes = row?.findViewById(R.id.io_oferta_antes)
        this.io_oferta_ahora = row?.findViewById(R.id.io_oferta_ahora)
        this.io_oferta_porcentaje = row?.findViewById(R.id.io_oferta_porcentaje)
        this.io_distancia = row?.findViewById(R.id.io_distancia)
        this.img = row?.findViewById(R.id.item_oferta_iv)
    }
}
