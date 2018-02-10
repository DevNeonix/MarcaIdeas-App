package com.jolver.nestor.marcaideas.Utils

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

/**
 * Created by Andre on 5/02/2018.
 */
fun Context.toast(mensaje:String="No se pudo establecer conexcion con el servidor",duracion:Int=Toast.LENGTH_SHORT)=Toast.makeText(this,mensaje,duracion).show()
fun View.snackBar(mensaje:String,duracion:Int=Snackbar.LENGTH_SHORT)=Snackbar.make(this,mensaje,duracion).show()