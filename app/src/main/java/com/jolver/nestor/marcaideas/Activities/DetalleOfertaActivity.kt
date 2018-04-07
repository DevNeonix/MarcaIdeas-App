package com.jolver.nestor.marcaideas.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import com.jolver.nestor.marcaideas.Models.Oferta
import com.jolver.nestor.marcaideas.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle_oferta.*

class DetalleOfertaActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var oferta: Oferta? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_oferta)

        binding()
        getOferta()
        sToolbar()

    }

    private fun getOferta() {
        var b: Bundle = intent.extras;
        oferta = Oferta(b.getInt("oferta__id"),
                b.getInt("oferta__lugar_id"),
                b.getString("oferta__producto"),
                b.getString("oferta__descripcion"),
                b.getDouble("oferta__precio_regular"),
                b.getDouble("oferta__precio_promocion"),
                b.getDouble("oferta__descuento"),
                b.getDouble("oferta__lat"),
                b.getDouble("oferta__lon"),
                b.getString("oferta__fecha_inicio"),
                b.getString("oferta__fecha_fin"),
                b.getString("oferta__image_url"),
                b.getString("oferta__condiciones"))
        Picasso.with(applicationContext).load(oferta!!.image_url).fit().centerCrop().into(ado_ivOferta)
        ado_tvahora.text = "$"+String.format("%.2f",oferta!!.precio_promocion)
        ado_tvantes.text = "$"+String.format("%.2f",oferta!!.precio_regular)
        ado_tvdescripcion.text=oferta!!.descripcion
        ado_tvahorro.text="Ahorro $"+String.format("%.2f",(oferta!!.precio_regular-oferta!!.precio_promocion))
        ado_tvdescuento.text=String.format("%.2f",oferta!!.descuento)+"Dcto."
        ado_tvcondiciones.text=oferta!!.condiciones
        ado_tvfecha.text=oferta!!.fecha_inicio +" al "+oferta!!.fecha_fin
        toolbar!!.title=oferta!!.producto
        toolbar!!.subtitle=oferta!!.descripcion
    }

    private fun sToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun binding() {
        toolbar = findViewById(R.id.ado_toolbar)
    }
}
