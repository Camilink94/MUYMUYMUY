package com.camilink.rrhh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.camilink.rrhh.R

class MainActivity : AppCompatActivity(),
    ListFragment.Listener,
    NewEmployeesFragment.Listener,
    DetailFragment.Listener {

    private lateinit var appBarConfiguration: AppBarConfiguration;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}