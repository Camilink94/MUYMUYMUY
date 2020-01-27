package com.camilink.rrhh.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.AllEmployeesListPresenterContract
import com.camilink.rrhh.presenter.contract.EmployeeDetailsPresenterContract
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment(),
    KoinComponent,
    EmployeeDetailsPresenterContract.IView, EmployeeListAdapter.Listener {

    private var listener: Listener? = null

    private val args: DetailFragmentArgs by navArgs()
    lateinit var employee: EmployeeModel

    private val presenter: EmployeeDetailsPresenterContract.IPresenter by inject {
        parametersOf(this)
    }

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
            presenter.markNewEmployee(employee.id, isChecked)
        }

        detail_rv.apply {
            layoutManager =
                LinearLayoutManager(
                    this@DetailFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = EmployeeListAdapter(this@DetailFragment)
        }

        presenter.getRespondingEmployes(employeeId = employee.id)
    }

    //region View
    override fun setRespondingEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "AAAAA")
        (detail_rv.adapter as EmployeeListAdapter).apply {
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
        (activity as MainActivity).showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }
    //endregion

    //region Adapter Listener
    override fun selectEmployee(employee: EmployeeModel) {
        val action = DetailFragmentDirections.actionDetailFragmentSelf(employee)
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
