package com.jolver.nestor.marcaideas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jolver.nestor.marcaideas.Models.Evento;

import java.util.List;

/**
 * Created by root on 31/01/18.
 */

public class EventoAdapter extends BaseAdapter {
    int layout;
    Context context;
    List<Evento> listado;

    public EventoAdapter(int layout, Context context, List<Evento> listado) {
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
        return listado.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        }
        return view;
    }
}
