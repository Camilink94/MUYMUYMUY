package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel

class EmployeePresenterContract {

    interface IPresenter {

        fun getLatestEmployees()
        fun getNewEmployees()
        fun markNewEmployee(employeeId: Int, new: Boolean)

    }

    interface IView {

        fun setLatestEmployees(employees: ArrayList<EmployeeModel>)
        fun setNewEmployees(employees: ArrayList<EmployeeModel>)

        fun connError()
        fun dataError()
    }
}