package com.example.aj.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aj.R

class Tab2Adapter(private var ar1: ArrayList<String>) : RecyclerView.Adapter<Tab2Adapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tab2_lists, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title1.setText(ar1[position])

    }

    override fun getItemCount(): Int {
        return ar1.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){


        var title1= view.findViewById<TextView>(R.id.text2)}

}
