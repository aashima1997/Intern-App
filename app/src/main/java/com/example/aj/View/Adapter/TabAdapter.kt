package com.example.aj.View.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.aj.View.Fragment.Tab2
import com.example.aj.View.Fragment.tab1


@Suppress("DEPRECATION")
class TabAdapter(var fm: FragmentManager, var totalTabs: Int):


    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                tab1()
            }
            1-> {
                Tab2()

            }
            else -> getItem(position)
        }
    }
        override fun getCount(): Int {
            return totalTabs
        }
    }
