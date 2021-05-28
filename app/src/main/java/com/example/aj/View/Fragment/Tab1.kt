package com.example.aj.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aj.R
import com.example.aj.View.Adapter.tab1Adapter

class tab1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab1, container, false)
        val recyclerView: RecyclerView
        recyclerView = view!!.findViewById(R.id.tab1recycle)
//array of images
         val images = intArrayOf(
             R.drawable.ii,
             R.drawable.jj,
             R.drawable.kk,
             R.drawable.ll,
             R.drawable.mm
         )


                recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = tab1Adapter(images)

        return view


    }
    }