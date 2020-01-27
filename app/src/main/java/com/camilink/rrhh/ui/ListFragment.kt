package com.camilink.rrhh.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.AllEmployeesListPresenterContract
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

class ListFragment : Fragment(),
    KoinComponent,
    AllEmployeesListPresenterContract.IView,
    EmployeeListAdapter.Listener {

    private val presenter: AllEmployeesListPresenterContract.IPresenter by inject {
        parametersOf(
            this
        )
    }

    private var listener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_showNewBtn.setOnClickListener { seeNewEmployees() }
        list_allRv.apply {
            layoutManager =
                LinearLayoutManager(this@ListFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = EmployeeListAdapter(this@ListFragment)
        }

        presenter.getLatestEmployees()
    }

    private fun seeNewEmployees() {
        val action = ListFragmentDirections.actionListFragmentToNewEmployeesFragment()
        findNavController().navigate(action)
    }

    //region View
    override fun setEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "Recieved ${employees.size} employees:\n$employees")

        (list_allRv.adapter as EmployeeListAdapter).apply {
            addAll(employees)
            notifyDataSetChanged()
        }
    }

    override fun connError() {

    }

    override fun dataError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
    //endregion

    //region Adapter Listener
    override fun selectEmployee(employee: EmployeeModel) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(employee)
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
