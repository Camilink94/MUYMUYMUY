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
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), EmployeeListAdapter.Listener {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: Listener? = null

    private val adapter: EmployeeListAdapter = EmployeeListAdapter(this)

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
            adapter = this@ListFragment.adapter
        }
    }

    private fun seeNewEmployees() {
        val action = ListFragmentDirections.actionListFragmentToNewEmployeesFragment()
        findNavController().navigate(action)
    }

    fun setLatestEmployees(employees: ArrayList<EmployeeModel>) {
        Log.d("AAAA", "Recieved ${employees.size} employees:\n$employees")

        (list_allRv.adapter as EmployeeListAdapter).apply {
            addAll(employees)
            notifyDataSetChanged()
        }
    }

    //region Adapter Listener
    override fun selectEmployee(employee: EmployeeModel) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(employee)
        findNavController().navigate(action)
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        listener?.markNewEmployeeFromList(employeeId, new)
    }
    //endregion

    interface Listener {
        fun markNewEmployeeFromList(employeeId: Int, new: Boolean)
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
