package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel

class NewEmployeeListPresenterContract {

    interface IPresenter {

        fun getNewEmployees()
    }

    interface IView {

        fun setNewEmployees(employees: ArrayList<EmployeeModel>)
    }
}