package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jolver.nestor.marcaideas.Models.Lugar;
import com.jolver.nestor.marcaideas.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by root on 31/01/18.
 */

public class LugarAdapter extends BaseAdapter {

    int layout;
    Context context;
    List<Lugar> listado;
    OnClick myOnclick;

    public LugarAdapter(int layout, Context context, List<Lugar> listado, OnClick myOnclick) {
        this.layout = layout;
        this.context = context;
        this.listado = listado;
        this.myOnclick = myOnclick;
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
        return listado.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        }
        final Lugar lugar = listado.get(i);

        LinearLayout ll = view.findViewById(R.id.ll_il);
        TextView tvnombre = view.findViewById(R.id.tvnombre_il);
        TextView tvlongitud = view.findViewById(R.id.tvlongitud_item_lugar);
        TextView tvdireccion = view.findViewById(R.id.tvdireccion_item_lugar);
        tvnombre.setText(lugar.getRazon_social());
        Picasso.with(context).load(lugar.getImage_url()).placeholder(R.drawable.logo).into((ImageView) view.findViewById(R.id.iv_item_lugar));
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnclick.getLugar(lugar);
            }
        });


        String lat = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lat", "");
        String lon = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lon", "");
//        Toast.makeText(context,lat+"-"+lon,Toast.LENGTH_SHORT).show();
        if(lat.equals("") && lon.equals("")){
            tvlongitud.setText("ubicación no encontrada");
        }else{
            Location loc1 = new Location("");
            loc1.setLatitude(lugar.getLat());
            loc1.setLongitude(lugar.getLon());

            Location loc2 = new Location("");
            loc2.setLatitude(Double.parseDouble(lat));
            loc2.setLongitude(Double.parseDouble(lon));

            double distanceInMeters = loc2.distanceTo(loc1)/1000;
            tvlongitud.setText(String.format("%.2f",distanceInMeters)+" km");
//            Toast.makeText(context,distanceInMeters+"",Toast.LENGTH_SHORT).show();
        }
        tvdireccion.setText(lugar.getDireccion()+"-"+lugar.getUbicacion());


        return view;
    }

    public interface OnClick {
        void getLugar(Lugar lugar);
    }
}
