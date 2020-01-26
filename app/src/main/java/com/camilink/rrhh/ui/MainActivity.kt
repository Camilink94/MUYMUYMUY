package com.camilink.rrhh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.EmployeePresenterContract
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(),
    KoinComponent,
    EmployeePresenterContract.IView,
    //Fragments
    ListFragment.Listener,
    NewEmployeesFragment.Listener,
    DetailFragment.Listener {

    private val presenter: EmployeePresenterContract.IPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.getLatestEmployees()

    }

    //region View
    override fun setLatestEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "Recieved ${employees.size} employees:\n$employees")
    }

    override fun connError() {

    }

    override fun dataError() {

    }
    //endregion

}