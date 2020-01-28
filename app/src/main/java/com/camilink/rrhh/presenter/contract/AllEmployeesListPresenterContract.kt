package com.camilink.rrhh.presenter.contract

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.BaseView
import com.camilink.rrhh.util.ListOrder

class AllEmployeesListPresenterContract {

    interface IPresenter {

        fun getAllEmployees(order: ListOrder)
        fun getLatestEmployees()
        fun getFiltered(query: String, order: ListOrder)

        fun markNewEmployee(employeeId: Int, new: Boolean)

    }

    interface IView : BaseView {

        fun setEmployees(employees: ArrayList<EmployeeModel>)

        fun markNewEmployeeSuccess()
        fun markNewEmployeeNotExists(employeeId: Int, new: Boolean)

        fun connError()
        fun dataError()
    }
}