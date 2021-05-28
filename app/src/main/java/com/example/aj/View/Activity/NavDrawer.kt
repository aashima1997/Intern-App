package com.example.aj.View.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.aj.*
import com.example.aj.View.Fragment.*
import com.google.android.material.navigation.NavigationView

class NavDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val drawerLayout by lazy {findViewById<DrawerLayout>(R.id.drawer_layout)}
    val navigationView by lazy {findViewById<NavigationView>(R.id.nav_view)}
    val toolbar by lazy {findViewById<Toolbar>(R.id.toolbar)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)






        setSupportActionBar(toolbar)
        val  toggle= ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

    }



//implemented method of NavigationItemSelected
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.attend) {
            val intent=Intent(this, Attend::class.java)
            startActivity(intent)
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.tts) {
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.fragment_container, TTS_STT()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.dt) {
            val fragment1 = supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.fragment_container, DateAndTimePicker()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.loc) {
            val fragment1 = supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.fragment_container, Location()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.tabss) {
            val fragment1 = supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.fragment_container, Tabs()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.imageEdit) {
            val fragment1 = supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.fragment_container, ImageEditor()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.weather) {
            val fragment1 = supportFragmentManager.beginTransaction()
            fragment1.replace(R.id.fragment_container, Weather()).commit()
            drawerLayout.closeDrawers()
        }
        if (item.itemId == R.id.settings) {
           val intent=Intent(this, SettingsAct::class.java)
            startActivity(intent)
            drawerLayout.closeDrawers()
        }
    if (item.itemId == R.id.pdf) {
        val fragment1 = supportFragmentManager.beginTransaction()
        fragment1.replace(R.id.fragment_container, pdfViewer()).commit()
        drawerLayout.closeDrawers()
    }
    if (item.itemId == R.id.alarm) {
        val intent = Intent(this, AlarmManager::class.java)
        startActivity(intent)
        drawerLayout.closeDrawers()
    }

        return true
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed()
        }
    }


}
