package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            //inflo la vista con cada item de grupo que tenga en la colexion
            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        }
        final Lugar lugar=listado.get(i);

        final LinearLayout ll=view.findViewById(R.id.ll_il);
        TextView tvnombre=view.findViewById(R.id.tvnombre_il);
        final LinearLayout llofertas=view.findViewById(R.id.il_llofertas);

        if(context.getSharedPreferences("marcaideas",Context.MODE_PRIVATE).getString("id","").equals("")){
            llofertas.setVisibility(View.GONE);
        }else{
            llofertas.setVisibility(View.VISIBLE);
        }


        tvnombre.setText(lugar.getRazon_social());
        Picasso.with(context).load(lugar.getImage_url()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ll.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOnclick.getLugar(lugar);
            }
        });



        return view;
    }

    public interface OnClick{
        void getLugar(Lugar lugar);
    }
}
