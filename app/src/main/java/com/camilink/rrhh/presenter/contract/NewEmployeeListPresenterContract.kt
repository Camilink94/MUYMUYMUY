package com.camilink.rrhh.presenter.contract

import com.camilink.rrhh.models.EmployeeModel

class NewEmployeeListPresenterContract {

    interface IPresenter {

        fun getNewEmployees()
        fun markNewEmployee(employeeId: Int, new: Boolean)
    }

    interface IView : BaseView {

        fun setNewEmployees(employees: ArrayList<EmployeeModel>)

        fun markNewEmployeeSuccess()
        fun markNewEmployeeNotExists(employeeId: Int)
    }
}