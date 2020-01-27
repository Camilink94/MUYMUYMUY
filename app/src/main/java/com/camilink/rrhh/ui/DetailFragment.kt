package com.camilink.rrhh.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.EmployeeDetailsPresenterContract
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.core.KoinComponent

class DetailFragment : Fragment(),
    KoinComponent,
    EmployeeDetailsPresenterContract.IView {

    private var listener: Listener? = null

    private val args: DetailFragmentArgs by navArgs()
    lateinit var employee: EmployeeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employee = args.employee

        detail_name.text = employee.name
        detail_position.text = employee.position
        detail_new.isChecked = employee.isNew

        detail_new.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }

    //region View
    override fun setRespondingEmployees(employees: ArrayList<EmployeeModel>) {

    }

    override fun markNewEmployeeSuccess() {

    }

    override fun markNewEmployeeNotExists(employeeId: Int) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
    //endregion

    interface Listener {
    }

    //region Attach Listener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    //endregion
}
