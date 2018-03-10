package com.jolver.nestor.marcaideas.Adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.jolver.nestor.marcaideas.Models.Grupo

import com.jolver.nestor.marcaideas.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.jolver.nestor.marcaideas.R.id.imageView
import android.R.attr.radius
import android.R.attr.path
import android.graphics.Typeface
import android.widget.LinearLayout
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


/**
 * Created by root on 30/01/18.
 */

class GrupoAdapter(//obtengo el layout q voy a usar
        internal var layout: Int, //el contexto
        internal var context: Context, //la colexion de datos q traigo del server
        internal var listado: List<Grupo>, private val myonClick: OnClick) : BaseAdapter() {

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

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        var tf:Typeface=Typeface.createFromAsset(context.assets,"fonts/kavivanar.ttf")
        var grupo_item=listado[i]
        vh.tvnombre!!.text = grupo_item.nombre
        vh.tvnombre!!.typeface=tf


        Picasso.with(context)
                .load(grupo_item.image_url)
                .transform(RoundedCornersTransformation(5, 5))
                .resize(150, 150)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .into(vh.img)


        vh.cd!!.setOnClickListener { myonClick.click(grupo_item.id.toString() + "") }








        return view!!
    }


    interface OnClick {
        fun click(id: String)
    }
}

private class ListRowHolder(row: View?) {
    var tvnombre: TextView?=null
    var img: ImageView?=null
    var cd:CardView?=null

    init {
        this.tvnombre = row?.findViewById(R.id.ig_tv)
        this.cd = row?.findViewById(R.id.ig_cdv)
        this.img = row?.findViewById(R.id.ig_iv_grupo)
    }
}