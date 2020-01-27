package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel

class AllEmployeesListPresenterContract {

    interface IPresenter {

        fun getAllEmployees()
        fun getLatestEmployees()
        fun markNewEmployee(employeeId: Int, new: Boolean)

    }

    interface IView : BaseView {

        fun setEmployees(employees: ArrayList<EmployeeModel>)

        fun connError()
        fun dataError()
    }
}