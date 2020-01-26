package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.repository.EmployeeRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class EmployeePresenter(private val view: EmployeePresenterContract.IView) :
    KoinComponent,
    EmployeePresenterContract.IPresenter,
    EmployeeRepository.Listener {

    private val repository by inject<EmployeeRepository> { parametersOf(this) }

    override fun getLatestEmployees() {
        repository.getLatestEmployees()
    }

    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setLatestEmployees(employees)
    }

    override fun connError() {
        view.connError()
    }

    override fun dataError() {
        view.dataError()
    }

}