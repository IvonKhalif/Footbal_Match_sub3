package com.ivonkhalif.ragnarok.footballlive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.ivonkhalif.ragnarok.footballlive.Adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = view_pager
        tabLayout = tab_layout
        tabAdapter = TabAdapter(supportFragmentManager)
//        val tabLayout : TabLayout = find(R.id.tab_layout)
//        val viewPager : ViewPager = find(R.id.view_pager)
//        val tabPageAdapter = TabAdapter(supportFragmentManager)

        viewPager.adapter = tabAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}
