package com.example.appclima.ui.CitiesActivity

import android.os.Bundle
import com.example.appclima.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class CitiesActivity: AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnNavigationItemSelectedListener {menuItem->
            when (menuItem.itemId){
                R.id.navigation_reloj->{
                    val relojFragment = RelojFragment()
                    cambiarFragment(relojFragment)
                }
                R.id.navigation_calidad->{
                    val calidadAireFragment = CalidadAireFragment()
                    cambiarFragment(calidadAireFragment)
                }
            }
            true
        }
        val relojFragment = RelojFragment()
        cambiarFragment(relojFragment)

    }

    private fun cambiarFragment(fragment:Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main,fragment)
        transaction.commit()
    }
}