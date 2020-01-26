package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel

class EmployeePresenterContract {

    interface IPresenter {

        fun getLatestEmployees()

    }

    interface IView {

        fun setLatestEmployees(employees: ArrayList<EmployeeModel>)

        fun connError()
        fun dataError()
    }
}