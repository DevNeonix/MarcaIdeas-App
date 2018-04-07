package com.jolver.nestor.marcaideas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jolver.nestor.marcaideas.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderItemFragment extends Fragment {
    View rView;

    public SliderItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rView=inflater.inflate(R.layout.fragment_slider_item, container, false);
        String url = getArguments().getString("image");
        Picasso.with(getContext()).load(url).placeholder(R.drawable.logo).fit().centerCrop().into((ImageView) rView.findViewById(R.id.iv_item_slider));

        return rView;
    }

    @NotNull
    public static Fragment newInstance(@NotNull String s) {
        SliderItemFragment f = new SliderItemFragment();

        Bundle bdl = new Bundle(1);

        bdl.putString("image", s);

        f.setArguments(bdl);

        return f;
    }
}
