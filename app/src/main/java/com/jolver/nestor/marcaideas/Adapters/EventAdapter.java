package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.maps.MapView;
import com.jolver.nestor.marcaideas.Models.Event;
import com.jolver.nestor.marcaideas.Models.Lugar;
import com.jolver.nestor.marcaideas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andre on 16/03/2018.
 */

public class EventAdapter extends BaseAdapter {

    int layout;
    Context context;
    List<Event> listado;

    public EventAdapter(int layout, Context context, List<Event> listado) {
        this.layout = layout;
        this.context = context;
        this.listado = listado;
    }

    @Override
    public int getCount() {
        return listado.size();
    }

    @Override
    public Object getItem(int i) {
        return listado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        }

        Event evento = listado.get(i);

        ImageView img = view.findViewById(R.id.iv_event_item);
        TextView tvnombre = view.findViewById(R.id.tv_nombre_event_item);
        TextView tvdescripcion = view.findViewById(R.id.tv_descripcion_event_item);
        TextView tvfecha = view.findViewById(R.id.tv_fecha_event_item);
        TextView tvubicacion = view.findViewById(R.id.tv_ubicacion__event_item);
        TextView tvdistancia = view.findViewById(R.id.tv_distancia_event_item);


        tvnombre.setText(evento.getNombre());
        if(evento.getDescripcion().length()>50){
            tvdescripcion.setText(evento.getDescripcion().substring(0,50)+"...");
        }else{
            tvdescripcion.setText(evento.getDescripcion());
        }
        tvfecha.setText(evento.getFecha_inicio() + " - " + evento.getFecha_fin());
        tvubicacion.setText(evento.getUbicacion());
        Picasso.with(context).load(evento.getImage_url()).placeholder(R.drawable.logo).into(img);

        String lat = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lat", "");
        String lon = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lon", "");
        if (lat.equals("") && lon.equals("")) {
            tvdistancia.setText("ubicación no encontrada");
        } else {
            Location loc1 = new Location("");
            loc1.setLatitude(evento.getLat());
            loc1.setLongitude(evento.getLon());

            Location loc2 = new Location("");
            loc2.setLatitude(Double.parseDouble(lat));
            loc2.setLongitude(Double.parseDouble(lon));

            double distanceInMeters = loc2.distanceTo(loc1) / 1000;
            tvdistancia.setText(String.format("%.2f",distanceInMeters)+" km");

        }

        return view;
    }

}
