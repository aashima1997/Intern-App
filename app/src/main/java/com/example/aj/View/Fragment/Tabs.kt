package com.example.aj.View.Fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.aj.R
import com.example.aj.View.Adapter.TabAdapter
import com.example.aj.View.Activity.NavDrawer
import com.google.android.material.tabs.TabLayout

@Suppress("DEPRECATION")
class Tabs : Fragment() {

lateinit var tabLayout:TabLayout
lateinit var viewpager:ViewPager
lateinit var toolbar1: Toolbar

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_tabs, container, false)

        val tabLayout=view!!.findViewById<TabLayout>(R.id.tablayout)
        val viewPager=view!!.findViewById<ViewPager>(R.id.viewPager)
          toolbar1=view!!.findViewById<Toolbar>(R.id.toolbar)
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"))
        tabLayout.addTab(tabLayout.newTab().setText("Quotes"))
        val adapter= getFragmentManager()?.let { TabAdapter(it, tabLayout.tabCount) }
        viewPager.adapter=adapter

        //changing the tab  on clicking the tab icon
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
//when changing tab with swipe
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
toolbar1.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar1.setNavigationOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent=(Intent(context, NavDrawer::class.java))
                startActivity(intent)
            }

        })
        return view
    }



    }


