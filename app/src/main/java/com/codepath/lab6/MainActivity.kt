package com.codepath.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ParksFragment())
                .commit()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_parks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ParksFragment())
                        .commit()
                    true
                }

                R.id.nav_campgrounds -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CampgroundsFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}