package com.example.aj.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.aj.R

class tab1Adapter(private var arr: IntArray): RecyclerView.Adapter<tab1Adapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tab1_list, parent, false)
        return MyViewHolder(itemView)
    }
//holding to a position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.title1.setImageResource(arr[position])

    }

    override fun getItemCount(): Int {
        return arr.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){


        var title1= view.findViewById<ImageView>(R.id.tab1Image)}

}
