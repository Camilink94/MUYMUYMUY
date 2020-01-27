package com.camilink.rrhh.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camilink.rrhh.R
import com.camilink.rrhh.models.EmployeeModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_employee.*

class EmployeeListAdapter(val listener: Listener) :
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

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

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindHolder(employee: EmployeeModel) {

            holder_name.text = employee.name
            holder_position.text = employee.position
            holder_email.text = employee.email
            holder_phone.text = employee.phone
            holder_new.isChecked = employee.isNew

            holder_cv.setOnClickListener {
                listener.selectEmployee(employee)
            }

            holder_new.setOnCheckedChangeListener { buttonView, isChecked ->
                listener.markNewEmployee(employeeId = employee.id, new = isChecked)
            }

        }
    }

    interface Listener {
        fun selectEmployee(employee: EmployeeModel)
        fun markNewEmployee(employeeId: Int, new: Boolean)
    }

}