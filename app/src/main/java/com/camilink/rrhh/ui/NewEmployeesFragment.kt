package com.camilink.rrhh.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.AllEmployeesListPresenterContract
import com.camilink.rrhh.presenter.contract.NewEmployeeListPresenterContract
import kotlinx.android.synthetic.main.fragment_new_employees.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NewEmployeesFragment : Fragment(),
    KoinComponent,
    NewEmployeeListPresenterContract.IView,
    EmployeeListAdapter.Listener {

    private var listener: Listener? = null

    val presenter: NewEmployeeListPresenterContract.IPresenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_employees, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        new_allRv.apply {
            layoutManager =
                LinearLayoutManager(
                    this@NewEmployeesFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = EmployeeListAdapter(this@NewEmployeesFragment)
        }

        presenter.getNewEmployees()
    }

    //region View
    override fun setNewEmployees(employees: ArrayList<EmployeeModel>) {
        (new_allRv.adapter as EmployeeListAdapter).apply {
            clearAll()
            addAll(employees)
            notifyDataSetChanged()
        }
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

    //region Adapter Listener
    override fun selectEmployee(employee: EmployeeModel) {
        val action =
            NewEmployeesFragmentDirections.actionNewEmployeesFragmentToDetailFragment(employee)
        findNavController().navigate(action)
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        presenter.markNewEmployee(employeeId, new)
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
