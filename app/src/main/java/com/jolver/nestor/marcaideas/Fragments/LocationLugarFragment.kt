package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.location.Address
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jolver.nestor.marcaideas.R
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Geocoder
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.util.*
import java.io.IOException
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.MapsInitializer






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

            val sydney = LatLng(lat.toDouble(), lon.toDouble())
            googleMap!!.addMarker(MarkerOptions().position(sydney).title(preferences!!.getString("lugar_razon_social","")))


            val cameraPosition = CameraPosition.Builder().target(sydney).zoom(12f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        })

        return rootView

    }


}
