package com.camilink.rrhh.presenter.contract

import com.camilink.rrhh.models.EmployeeModel

class NewEmployeeListPresenterContract {

    interface IPresenter {

        fun getNewEmployees()
    }

    interface IView : BaseView {

        fun setNewEmployees(employees: ArrayList<EmployeeModel>)
    }
}