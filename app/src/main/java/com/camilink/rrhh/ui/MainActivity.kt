package com.camilink.rrhh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.camilink.rrhh.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(),
    KoinComponent,
    //Fragments
    ListFragment.Listener,
    NewEmployeesFragment.Listener,
    DetailFragment.Listener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration =
            AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    fun showLoading() {
        loadingGr.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loadingGr.visibility = View.GONE
    }

}