package com.jolver.nestor.marcaideas.Utils

import android.app.Dialog
import android.content.Context
import android.view.Window

/**
 * Created by Andre on 5/02/2018.
 */

fun CreateCustomDialog(context: Context, layout: Int, cancelable: Boolean = true): Dialog {
    var mydialog: Dialog = Dialog(context)
    mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    mydialog.setCancelable(cancelable)
    mydialog.setContentView(layout)
    return mydialog
}
