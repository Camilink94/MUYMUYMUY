package com.camilink.rrhh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.EmployeePresenterContract
import kotlinx.android.synthetic.main.activity_main.*
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

        presenter.getLatestEmployees()

    }

    //region View
    override fun setLatestEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "Recieved ${employees.size} employees:\n$employees")

        //Get current fragment in the nav host fragment. This is a "hack" to get the fragment, since
        //there's no documentation about how to communicate to the fragment using Navigation
        //except for ViewModel.
        val listFrag =
            nav_host_fragment.childFragmentManager.primaryNavigationFragment as? ListFragment
        Log.d("AAAA", "Is list null: ${listFrag == null}")
        listFrag?.setLatestEmployees(employees)
    }

    override fun connError() {

    }

    override fun dataError() {

    }
    //endregion

}