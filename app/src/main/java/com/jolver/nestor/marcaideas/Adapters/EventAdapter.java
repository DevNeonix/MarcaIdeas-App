package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
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
    OnLoadMap onLoadMap;

    public EventAdapter(int layout, Context context, List<Event> listado, OnLoadMap onLoadMap) {
        this.layout = layout;
        this.context = context;
        this.listado = listado;
        this.onLoadMap = onLoadMap;
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

        MapView mMapView = view.findViewById(R.id.mapView_event);
        ImageView img = view.findViewById(R.id.iv_event_item);
        TextView tvnombre = view.findViewById(R.id.tv_nombre_event_item);
        TextView tvdescripcion = view.findViewById(R.id.tv_descripcion_event_item);
        TextView tvfecha = view.findViewById(R.id.tv_fecha_event_item);
        TextView tvubicacion = view.findViewById(R.id.tv_ubicacion__event_item);


        Picasso.with(context).load(evento.getImageUrl()).placeholder(R.drawable.logo).into(img);

        onLoadMap.onMap(mMapView,evento.getLat(),evento.getLon());

        return view;
    }

    public interface OnLoadMap {
        void onMap(MapView map,Double lat,Double lon);
    }
}
