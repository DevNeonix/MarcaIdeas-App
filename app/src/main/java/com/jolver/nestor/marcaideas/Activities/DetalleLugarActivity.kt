package com.jolver.nestor.marcaideas.Activities

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.jolver.nestor.marcaideas.Adapters.MyPageSlideAdapter
import com.jolver.nestor.marcaideas.Adapters.PagerAdapter
import com.jolver.nestor.marcaideas.Fragments.SliderItemFragment
import com.jolver.nestor.marcaideas.Models.Imagenes
import com.jolver.nestor.marcaideas.R
import com.jolver.nestor.marcaideas.Services.ImagenesService
import com.jolver.nestor.marcaideas.Services.myRetrofit
import kotlinx.android.synthetic.main.activity_detalle_lugar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetalleLugarActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var preferences: SharedPreferences? = null

    var pageAdapter: MyPageSlideAdapter? = null


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





        var tablayout: TabLayout = findViewById(R.id.tabLayout)
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_info))
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_phone))
        tablayout.addTab(tablayout.newTab().setIcon(R.drawable.ic_tags1))
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

        var retrofit = myRetrofit
        var service = retrofit!!.create(ImagenesService::class.java).listado(lugar_id)


        service.enqueue(object : Callback<List<Imagenes>> {
            override fun onFailure(call: Call<List<Imagenes>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<List<Imagenes>>?, response: Response<List<Imagenes>>?) {
                if (response!!.body()!!.isEmpty()) {

                } else {

                    var fragments: List<Fragment> = getFragments(response!!.body()!!)
                    pageAdapter = MyPageSlideAdapter(supportFragmentManager, fragments);
                    adl_vp.adapter = pageAdapter
                }
            }

        })
    }

    private fun getFragments(i: List<Imagenes>): List<Fragment> {

        val fList = ArrayList<Fragment>()
        i.forEach {
            fList.add(SliderItemFragment.newInstance(it.img))
        }
        return fList

    }

}
