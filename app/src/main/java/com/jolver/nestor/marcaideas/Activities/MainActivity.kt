package com.jolver.nestor.marcaideas.Activities

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.akhgupta.easylocation.EasyLocationAppCompatActivity
import com.akhgupta.easylocation.EasyLocationRequest
import com.akhgupta.easylocation.EasyLocationRequestBuilder
import com.google.android.gms.location.LocationRequest
import com.jolver.nestor.marcaideas.Fragments.EventFragment
import com.jolver.nestor.marcaideas.Fragments.OfertasFragment
import com.jolver.nestor.marcaideas.Fragments.PerfilFragment
import com.jolver.nestor.marcaideas.Fragments.ServiciosFragment
import com.jolver.nestor.marcaideas.Models.Login
import com.jolver.nestor.marcaideas.Models.RUser
import com.jolver.nestor.marcaideas.Models.RespuestaGenerica
import com.jolver.nestor.marcaideas.Models.User

import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.LoginServices
import com.jolver.nestor.marcaideas.Services.RegisterUser
import com.jolver.nestor.marcaideas.Services.myRetrofit
import com.jolver.nestor.marcaideas.Utils.CreateCustomDialog
import com.jolver.nestor.marcaideas.Utils.toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mxn.soul.flowingdrawer_core.ElasticDrawer
import com.mxn.soul.flowingdrawer_core.FlowingDrawer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : EasyLocationAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {


    var toolbar: Toolbar? = null
    var mDrawer: FlowingDrawer? = null
    var navView: NavigationView? = null
    var tabs: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding()
        CambiarMenu()
        checkPermission()


        var locationRequest: LocationRequest = LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
                .setFastestInterval(5000)

        var easyLocationRequest: EasyLocationRequest = EasyLocationRequestBuilder()
                .setLocationRequest(locationRequest)
                .setFallBackToLastLocationTime(3000)
                .build()
        requestLocationUpdates(easyLocationRequest);


    }

    private fun binding() {

        toolbar = findViewById(R.id.toolbar)
        mDrawer = findViewById(R.id.drawerlayout)
        navView = findViewById(R.id.navView)
        tabs = findViewById(R.id.tabs_main)

        tabs!!.addTab(tabs!!.newTab().setIcon(R.drawable.ic_category))
        tabs!!.addTab(tabs!!.newTab().setIcon(R.drawable.ic_tags1))
        tabs!!.addTab(tabs!!.newTab().setIcon(R.drawable.ic_event))

        tabs!!.addOnTabSelectedListener(this)




        mDrawer!!.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL)
        mDrawer!!.setOnDrawerStateChangeListener(object : ElasticDrawer.OnDrawerStateChangeListener {
            override fun onDrawerStateChange(oldState: Int, newState: Int) {
                CambiarMenu()
            }

            override fun onDrawerSlide(openRatio: Float, offsetPixels: Int) {
                CambiarMenu()
            }

        })
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)


        navView!!.setNavigationItemSelectedListener(this)


        setFragmentByDefault()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                mDrawer!!.toggleMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragmentTransaction = false
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.mServicios -> {
                var tab:TabLayout.Tab= tabs!!.getTabAt(0)!!
                tab.select()

                fragmentTransaction = true
                fragment = ServiciosFragment()
            }
            R.id.mOfertas -> {
                var tab:TabLayout.Tab= tabs!!.getTabAt(1)!!
                tab.select()

                fragmentTransaction = true
                fragment = OfertasFragment()
            }
            R.id.mEventos -> {
                var tab:TabLayout.Tab= tabs!!.getTabAt(2)!!
                tab.select()
                fragmentTransaction = true
                fragment = EventFragment()
            }
            R.id.mInformacion -> {
                startActivity(Intent(this@MainActivity,ContactActivity::class.java));
            }
//            R.id.mPerfil -> {
//                fragmentTransaction = true
//                fragment = PerfilFragment()
//            }
            R.id.mRegistro -> {
                val dialog_registro = CreateCustomDialog(this@MainActivity, R.layout.dialog_registro)

                val llregistro: LinearLayout = dialog_registro.findViewById(R.id.llregistro)
                llregistro.layoutParams.width = findViewById<LinearLayout>(R.id.content).width - 50
                llregistro.requestLayout()
                dialog_registro.show()


                val input_name: EditText = dialog_registro.findViewById(R.id.dr_input_name)
                val input_email: EditText = dialog_registro.findViewById(R.id.dr_input_email)
                val input_password: EditText = dialog_registro.findViewById(R.id.dr_input_password)
                val btn_signup: Button = dialog_registro.findViewById(R.id.dr_btn_signup)

                val myRetrofit = myRetrofit
                val myService = myRetrofit.create(RegisterUser::class.java)

                btn_signup.setOnClickListener(View.OnClickListener {
                    if (input_email.text.equals("") || input_name.text.equals("") || input_password.text.equals("")) {
                        Toast.makeText(this@MainActivity, "Ingresa los datos correctamente.", Toast.LENGTH_SHORT).show()
                    } else {
                        val res = myService.register(RUser(input_name.getText().toString(), input_email.getText().toString(), input_password.getText().toString()))
                        res.enqueue(object : Callback<RespuestaGenerica> {
                            override fun onResponse(call: Call<RespuestaGenerica>, response: Response<RespuestaGenerica>) {
                                if (response.code() == 201) {
                                    Toast.makeText(this@MainActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                                    dialog_registro.dismiss()
                                } else if (response.code() == 400) {
                                    Toast.makeText(this@MainActivity, "Este email ya esta registrado.", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<RespuestaGenerica>, t: Throwable) {
                                Toast.makeText(this@MainActivity, "no se pudo establecer conexion con el servidor", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                })
            }

            R.id.mCerrarSesion -> {
                val preferences = getSharedPreferences("marcaideas", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("id", "")
                editor.putString("fullname", "")
                editor.putString("email", "")
                editor.putString("fecha_nacimiento", "")
                editor.putString("remember_token", "")
                editor.apply()
                mDrawer!!.closeMenu()
                CambiarMenu()
                setFragmentByDefault()
            }
            R.id.mLogin -> {
                var dialog: Dialog = CreateCustomDialog(this@MainActivity, R.layout.dialog_login)

                bindingDialogLogin(dialog)

                dialog.show()
            }
        }
        if (fragmentTransaction) {
            changeFragment(fragment!!, item)
            mDrawer!!.closeMenu()
        }
        return true;

    }

    private fun bindingDialogLogin(dialog_login: Dialog) {

        val ll: LinearLayout = dialog_login.findViewById(R.id.lllogin)
        ll.layoutParams.width = findViewById<LinearLayout>(R.id.content).width - 50
        ll.requestLayout()
        dialog_login.show()

        dialog_login.findViewById<CardView>(R.id.dl_btnLogin).setOnClickListener(View.OnClickListener {
            val etUsername: EditText = dialog_login.findViewById(R.id.dl_etUsername)
            val etPassword: EditText = dialog_login.findViewById(R.id.dl_etPassword)
            val preferences = getSharedPreferences("marcaideas", Context.MODE_PRIVATE)

            val myRetrofit = myRetrofit
            val myService = myRetrofit.create(LoginServices::class.java)
            val response = myService.login(Login(etUsername.getText().toString(), etPassword.getText().toString()))
            response.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    val status = response.code()
                    if (status == 200) {
                        val data: User = response.body()!!
                        Toast.makeText(applicationContext, "Bienvenido " + data.fullname, Toast.LENGTH_SHORT).show()
                        val editor = preferences.edit()
                        editor.putString("id", data.id.toString())
                        editor.putString("fullname", data.fullname)
                        editor.putString("email", data.email)
                        editor.putString("fecha_nacimiento", data.fecha_nacimiento)
                        editor.putString("remember_token", data.remember_token)
                        editor.putString("url_image", data.url_image)
                        editor.apply()
                        dialog_login.dismiss()
                        CambiarMenu()
                    } else {
                        Toast.makeText(applicationContext, "Usuario o Password errado", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext, "No se pudo conectar con el servidor intentelo mas tarde", Toast.LENGTH_SHORT).show()
                }
            })
        })


    }

    private fun CambiarMenu() {
        if (getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("id", "") == "") {
            navView!!.getMenu().findItem(R.id.mCerrarSesion).setVisible(false)
//            navView!!.getMenu().findItem(R.id.mPerfil).setVisible(false)
            navView!!.getMenu().findItem(R.id.mLogin).setVisible(true)
            navView!!.getMenu().findItem(R.id.mRegistro).setVisible(true)
//            navView!!.getMenu().findItem(R.id.mOfertas).setVisible(false)

        } else {

            navView!!.getMenu().findItem(R.id.mCerrarSesion).setVisible(true)
//            navView!!.getMenu().findItem(R.id.mPerfil).setVisible(true)
            navView!!.getMenu().findItem(R.id.mLogin).setVisible(false)
            navView!!.getMenu().findItem(R.id.mRegistro).setVisible(false)
//            navView!!.getMenu().findItem(R.id.mOfertas).setVisible(true)
        }
    }

    private fun setFragmentByDefault() {
        changeFragment(ServiciosFragment(), navView!!.menu.getItem(0))
    }

    private fun changeFragment(fragment: Fragment, item: MenuItem = navView!!.menu.getItem(1)) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()
        item.isChecked = true
        supportActionBar!!.title = item.title
    }

    override fun onResume() {
        super.onResume()
        CambiarMenu()
    }

    private fun checkPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {
                            for (permission in report.grantedPermissionResponses) {
                                when (permission.permissionName) {
                                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                                    }
                                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    }
                                    Manifest.permission.CALL_PHONE -> {
                                    }
                                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                    }
                                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                                    }
                                }
                            }
                            for (permission in report.deniedPermissionResponses) {
                                when (permission.permissionName) {
                                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                                        if (permission.isPermanentlyDenied) {
                                            toast("Se denego el acceso")
                                        } else {
                                            toast("Se denego el acceso")
                                        }
                                    }
                                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                                        if (permission.isPermanentlyDenied) {
                                            toast("Se denego el acceso")
                                        } else {
                                            toast("Se denego el acceso")
                                        }
                                    }
                                    Manifest.permission.CALL_PHONE -> {
                                        if (permission.isPermanentlyDenied) {
                                            toast("Se denego el acceso")
                                        } else {
                                            toast("Se denego el acceso")
                                        }
                                    }
                                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                        if (permission.isPermanentlyDenied) {
                                            toast("Se denego el acceso")
                                        } else {
                                            toast("Se denego el acceso")
                                        }
                                    }
                                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                                        if (permission.isPermanentlyDenied) {
                                            toast("Se denego el acceso")
                                        } else {
                                            toast("Se denego el acceso")
                                        }
                                    }
                                }
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                        token?.continuePermissionRequest()
                    }
                }).check()
    }

    private var backPressedTime: Long = 0    // used by onBackPressed()

    override fun onBackPressed() {
        val t = System.currentTimeMillis()
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t
            toast("Presiona atráz otra vez para salir de la app")
        } else {    // this guy is serious
            // clean up
            super.onBackPressed()       // bye
        }
    }


    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        var position:Int = tab!!.position;
        when(position){
            0->{
                changeFragment(ServiciosFragment(), navView!!.menu.getItem(0))

            }
            1->{
                changeFragment(OfertasFragment(), navView!!.menu.getItem(1))

            }
            2->{
                changeFragment(EventFragment(), navView!!.menu.getItem(2))

            }
        }
    }

    override fun onLocationProviderDisabled() {

    }

    override fun onLocationPermissionGranted() {

    }

    override fun onLocationProviderEnabled() {

    }

    override fun onLocationPermissionDenied() {

    }

    override fun onLocationReceived(location: Location?) {
        var editor = getSharedPreferences("marcaideas", Context.MODE_PRIVATE).edit();
        editor.putString("lat", location!!.latitude.toString())
        editor.putString("lon", location!!.longitude.toString())
        editor.apply()
//        toast("latitud: ${location.latitude} longitud: ${location.longitude}")
    }

}
