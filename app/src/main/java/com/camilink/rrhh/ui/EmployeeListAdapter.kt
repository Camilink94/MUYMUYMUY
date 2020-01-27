package com.camilink.rrhh.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    private val employees = ArrayList<EmployeeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.holder_employee, parent, false))
    }

    override fun getItemCount(): Int = employees.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindHolder(employees[position])

    fun clearAll() = employees.clear()

    fun addAll(newEmployees: ArrayList<EmployeeModel>) {
        employees.addAll(newEmployees)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindHolder(employee: EmployeeModel) {

        }

    }
}