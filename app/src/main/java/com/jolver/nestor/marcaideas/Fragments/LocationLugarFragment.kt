package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.location.Address
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jolver.nestor.marcaideas.R
import android.location.Geocoder
import com.google.android.gms.maps.*
import java.util.*
import java.io.IOException
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.*


/**
 * A simple [Fragment] subclass.
 */
class LocationLugarFragment : Fragment() {
    var rootView:View?=null


    var mMapView: MapView? = null
    var googleMap: GoogleMap? = null
    var preferences:SharedPreferences?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_location_lugar, container, false)

        preferences=context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE)



        mMapView = rootView!!.findViewById(R.id.mapView)
        mMapView!!.onCreate(savedInstanceState)

        mMapView!!.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }


        mMapView!!.getMapAsync(OnMapReadyCallback { mMap ->
            googleMap = mMap

            var lat=preferences!!.getString("lugar_lat","")
            var lon=preferences!!.getString("lugar_lon","")

            var mlat=preferences!!.getString("lat","")
            var mlon=preferences!!.getString("lon","")

            val lugar = LatLng(lat.toDouble(), lon.toDouble())
            val me = LatLng(mlat.toDouble(), mlon.toDouble())

            val iconLugar = BitmapDescriptorFactory.fromResource(R.drawable.ic_lugar)
            val iconMe = BitmapDescriptorFactory.fromResource(R.drawable.ic_me)


            googleMap!!.addMarker(MarkerOptions().position(lugar).title(preferences!!.getString("lugar_razon_social","")).icon(iconLugar))
            googleMap!!.addMarker(MarkerOptions().position(me).title("Yo").icon(iconMe))
            googleMap!!.addPolyline(PolylineOptions()
                    .add(me, lugar )
                    .width("8".toFloat())
                    .color(Color.DKGRAY));

            val cameraPosition = CameraPosition.Builder().target(lugar).zoom(12f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        })

        return rootView

    }


}
