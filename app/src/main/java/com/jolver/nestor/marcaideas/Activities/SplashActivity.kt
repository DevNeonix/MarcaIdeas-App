package com.jolver.nestor.marcaideas.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import com.jolver.nestor.marcaideas.R
import gr.net.maroulis.library.EasySplashScreen



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = EasySplashScreen(this@SplashActivity)
                .withFullScreen()
                .withTargetActivity(MainActivity::class.java)
                .withSplashTimeOut(4000)
                .withBackgroundResource(R.color.colorApplication)
                .withLogo(R.drawable.logo)

        val easySplashScreenView = config.create()
        setContentView(easySplashScreenView)


    }
}
