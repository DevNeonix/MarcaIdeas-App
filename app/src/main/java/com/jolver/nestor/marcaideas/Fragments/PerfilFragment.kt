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
class PerfilFragment : Fragment() {
    lateinit var viewRoot:View


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewRoot=inflater!!.inflate(R.layout.fragment_perfil, container, false)
        return viewRoot
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
