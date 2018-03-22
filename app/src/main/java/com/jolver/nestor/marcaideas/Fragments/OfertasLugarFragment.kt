package com.jolver.nestor.marcaideas.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jolver.nestor.marcaideas.R


/**
 * A simple [Fragment] subclass.
 */
class OfertasLugarFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_ofertas_lugar, container, false)
    }

}// Required empty public constructor
