package com.jolver.nestor.marcaideas.Activities

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import com.jolver.nestor.marcaideas.Adapters.PagerAdapter
import com.jolver.nestor.marcaideas.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle_lugar.*

class DetalleLugarActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var preferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_lugar)

        toolbar = findViewById(R.id.adl_toolbar)
        setSupportActionBar(toolbar!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var Bundle = intent.extras
        if (Bundle.isEmpty) {
            finish()
        }
        toolbar!!.setTitle(Bundle.getString("rs"))


        var lugar_id = getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lugar_id", "")

        if (lugar_id.equals("")) {
            finish()
        }

        Picasso.with(applicationContext).load(getSharedPreferences("marcaideas", Context.MODE_PRIVATE).getString("lugar_image_url", "")).into(adl_iv)
        var tablayout: TabLayout = findViewById(R.id.tabLayout)
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_info))
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_phone))
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_map_white))


        var viewpager: ViewPager = findViewById(R.id.viewPager)
        val adapter = PagerAdapter(supportFragmentManager, tablayout.tabCount)

        viewpager.adapter = adapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))

        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                viewpager.currentItem = position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


    }
}
