package com.camilink.rrhh.presenter.contract

import com.camilink.rrhh.models.EmployeeModel

class EmployeeDetailsPresenterContract {
    interface IPresenter {
        fun getRespondingEmployes(employeeId: Int)
        fun markNewEmployee(employeeId: Int, new: Boolean)
    }

    interface IView : BaseView {
        fun setRespondingEmployees(employees: ArrayList<EmployeeModel>)

        fun markNewEmployeeSuccess()
        fun markNewEmployeeNotExists(employeeId: Int)
    }
}
