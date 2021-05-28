package com.example.aj.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aj.R
import com.example.aj.DataRepository.RoomDB.Attendees

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<Attendees>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(view)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holding to a position
        val currentItem = userList[position]
        holder.title1.text = currentItem.remember.toString()
        holder.title2.text= currentItem.Name
        holder.title2.text= currentItem.Present
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(View: View) : RecyclerView.ViewHolder(View) {
        fun bind(user1: Attendees) {
            //setting the values
            title1.text = user1.remember.toString()
            title2.text= user1.Name.toString()
            title3.text=user1.Present
        }

        var title1 = View!!.findViewById<TextView>(R.id.date)
        var title2 = View!!.findViewById<TextView>(R.id.username)
        var title3=View!!.findViewById<TextView>(R.id.rad1)
    }

    fun setData(user1: List<Attendees>) {
        this.userList = user1
        //any changes, notified
        notifyDataSetChanged()
    }

}

