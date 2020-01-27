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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: Listener? = null

    private val adapter: EmployeeListAdapter = EmployeeListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

    interface Listener {
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
