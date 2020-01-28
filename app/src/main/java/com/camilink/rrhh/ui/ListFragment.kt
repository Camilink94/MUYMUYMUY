package com.camilink.rrhh.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.AllEmployeesListPresenterContract
import com.camilink.rrhh.util.ListOrder
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.parameter.parametersOf

class ListFragment : Fragment(),
    KoinComponent,
    AllEmployeesListPresenterContract.IView,
    EmployeeListAdapter.Listener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private val presenter: AllEmployeesListPresenterContract.IPresenter by inject {
        parametersOf(this)
    }

    private var listener: Listener? = null

    private val listOrders = ListOrder.values()

    private var isSearching = false
    private var query = ""
    private var currentOrder: ListOrder = ListOrder.NONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderAdapter = ArrayAdapter<ListOrder>(
            context!!,
            android.R.layout.simple_spinner_item,
            listOrders
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        list_orderSp.adapter = orderAdapter
        list_orderSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                handleOrder(listOrders[position])
            }
        }

        list_search.setOnQueryTextListener(this)
        list_search.setOnCloseListener(this)

        list_allRv.apply {
            layoutManager =
                LinearLayoutManager(this@ListFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = EmployeeListAdapter(this@ListFragment)
        }

        list_reload.setOnClickListener { reloadEmployees() }
        list_showNewBtn.setOnClickListener { seeNewEmployees() }

        presenter.getLatestEmployees()
    }

    private fun handleOrder(order: ListOrder) {
        currentOrder = order

        if (isSearching) {
            presenter.getFiltered(query, currentOrder)
        } else {
            presenter.getAllEmployees(currentOrder)
        }
    }

    private fun reloadEmployees() {
        presenter.getLatestEmployees()
    }

    private fun seeNewEmployees() {
        val action = ListFragmentDirections.actionListFragmentToNewEmployeesFragment()
        findNavController().navigate(action)
    }

    //region Search
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        if (newText.isNotBlank()) {
            isSearching = true
            query = newText
            presenter.getFiltered(query, currentOrder)
        } else {
            isSearching = false
            query = newText
            presenter.getAllEmployees(currentOrder)
        }
        return false
    }

    override fun onClose(): Boolean {
        isSearching = false
        query = ""
        presenter.getAllEmployees(currentOrder)
        return false
    }
    //endregion

    //region View
    override fun setEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "Recieved ${employees.size} employees:\n$employees")

        (list_allRv.adapter as EmployeeListAdapter).apply {
            clearAll()
            addAll(employees)
            notifyDataSetChanged()
        }

        var cacheBool = false
        for (employee in employees) {
            cacheBool = cacheBool || employee.isNew
            if (cacheBool) break
        }
        list_showNewBtn.visibility = if (cacheBool) View.VISIBLE else View.GONE
    }

    override fun markNewEmployeeSuccess() {

    }

    override fun markNewEmployeeNotExists(employeeId: Int, new: Boolean) {
        (list_allRv.adapter as EmployeeListAdapter).apply {
            employees.find { id == employeeId }?.isNew = !new
            notifyDataSetChanged()
        }
    }

    override fun connError() {

    }

    override fun dataError() {

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
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(employee)
        findNavController().navigate(action)
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        Log.d("AAAA", "Mark from fragment")
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
