package com.camilink.rrhh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camilink.rrhh.R
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

    }

    //region ListFragment Listener

    //endregion

}