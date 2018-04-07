package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jolver.nestor.marcaideas.Models.Categoria;
import com.jolver.nestor.marcaideas.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by root on 31/01/18.
 */

public class CategoriaAdapter extends BaseAdapter {

    int layout;
    Context context;
    List<Categoria> listado;
    private HicisteClick miOnClick;

    public CategoriaAdapter(int layout, Context context, List<Categoria> listado, HicisteClick miOnClick) {
        this.layout = layout;
        this.context = context;
        this.listado = listado;
        this.miOnClick = miOnClick;
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
        binding(view, listado.get(i));


        return view;
    }

    private void binding(View view, final Categoria categoria_item) {
        TextView tvnombre = view.findViewById(R.id.tv_categoria_nombre);
        final CardView cd = view.findViewById(R.id.llitem_categoria);
        ImageView img = view.findViewById(R.id.iv_icategoria);
        tvnombre.setText(categoria_item.getNombre());


        Picasso.with(context).load(categoria_item.getImage_url()).fit().centerCrop().placeholder(R.mipmap.ic_logo)
                .into(img);


        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miOnClick.MeDevuelveElId(categoria_item);
            }
        });
    }

    public interface HicisteClick {
        void MeDevuelveElId(Categoria categoria);
    }
}
