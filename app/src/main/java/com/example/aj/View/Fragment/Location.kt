package com.example.aj.View.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.aj.R
import com.example.aj.View.Activity.NavDrawer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


@Suppress("DEPRECATION")
class Location : Fragment(), OnMapReadyCallback {
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_location, container, false)
    val mapFragment =
        activity!!.fragmentManager.findFragmentById(R.id.mapNearBy) as MapFragment
    mapFragment?.getMapAsync(this)
    val  toolbar1=view!!.findViewById<Toolbar>(R.id.toolbar)

    //toolbar with icon
    toolbar1.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

    //navigating to navDrawer Activity
   toolbar1.setNavigationOnClickListener(object :View.OnClickListener {
       override fun onClick(v: View?) {
           val intent = (Intent(context, NavDrawer::class.java))
           startActivity(intent)
       }
        })
        return view
    }

//implemented methods of OnMapReadyCallback
    override fun onMapReady(googleMap: GoogleMap) {
//provided lat long
        val Madurai = LatLng(9.9252, 78.1198)
        googleMap.addMarker(
            MarkerOptions()
                .position(Madurai)
                .title("Destination 1")
        )
    //provided lat long
    val Dindigul = LatLng(10.3624, 77.9695)
    googleMap.addMarker(
        MarkerOptions()
            .position(Dindigul)
            .title("Destination 2")
    )


    }    }

