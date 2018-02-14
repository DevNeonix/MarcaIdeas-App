package com.jolver.nestor.marcaideas.Fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jolver.nestor.marcaideas.R
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import com.jolver.nestor.marcaideas.Utils.toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.jar.Manifest


/**
 * A simple [Fragment] subclass.
 */
class ContactoLugarFragment : Fragment() {
    var viewRoot: View? = null
    var iv_llamarempresa: ImageView? = null
    var tv_llamarempresa: TextView? = null
    var preferences: SharedPreferences? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewRoot = inflater!!.inflate(R.layout.fragment_contacto_lugar, container, false)

        preferences = context.getSharedPreferences("marcaideas", Context.MODE_PRIVATE)

        return viewRoot
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var numero = preferences!!.getString("lugar_telefono", "")
        iv_llamarempresa = viewRoot!!.findViewById(R.id.iv_llamarempresa)
        tv_llamarempresa = viewRoot!!.findViewById(R.id.tv_llamarempresa)
        tv_llamarempresa!!.text = numero

        iv_llamarempresa!!.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                Dexter.withActivity(activity).withPermission(android.Manifest.permission.CALL_PHONE).withListener(
                        object : PermissionListener {
                            override fun onPermissionGranted(response: PermissionGrantedResponse?) {

                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = Uri.parse("tel:" + numero)
                                if (!numero.equals("")) {
                                    startActivity(callIntent)
                                }
                            }

                            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                                token?.continuePermissionRequest()
                            }

                            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                                if (response!!.isPermanentlyDenied) {
                                    context.toast("Se necesitan los permisos, si denegaste la peticion activala en settings", Toast.LENGTH_LONG)
                                } else {
                                    context.toast("Se necesitan los permisos", Toast.LENGTH_LONG)
                                }
                            }

                        }
                ).check()

            } else {
                var numero = preferences!!.getString("lugar_telefono", "")
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + numero)
                if (!numero.equals("")) {
                    startActivity(callIntent)
                }
            }
        }
    }
}
