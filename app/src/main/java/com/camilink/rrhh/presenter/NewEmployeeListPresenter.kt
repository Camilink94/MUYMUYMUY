package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.NewEmployeeListPresenterContract
import com.camilink.rrhh.repository.EmployeeRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NewEmployeeListPresenter(val view: NewEmployeeListPresenterContract.IView) :
    KoinComponent,
    NewEmployeeListPresenterContract.IPresenter,
    EmployeeRepository.Listener {

    private val repository by inject<EmployeeRepository> { parametersOf(this) }

    override fun getNewEmployees() {

    }

    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setNewEmployees(employees)
    }

    override fun connError() {
        
    }

    override fun dataError() {
        
    }

    override fun markNewEmployeeSuccess() {
        
    }

    override fun markNewEmployeeNotExists() {
        
    }
}