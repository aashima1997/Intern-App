package com.example.aj.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aj.R
import com.example.aj.View.Adapter.ListAdapter
import com.example.aj.ViewModel.LoginViewModel

class Attend : AppCompatActivity() {
    lateinit var mUserViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attend)
        val adapter= ListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val  toolbar1=findViewById<Toolbar>(R.id.toolbar)
        toolbar1.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar1.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = (Intent(this@Attend, NavDrawer::class.java))
                startActivity(intent)
            }
        })
        recyclerView.adapter =adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this)
        mUserViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(
            LoginViewModel::class.java
        )
        mUserViewModel.readAllData1.observe(this, Observer { user1 ->
            adapter.setData(user1)

        })
    }
}