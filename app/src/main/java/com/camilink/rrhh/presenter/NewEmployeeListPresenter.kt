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

    //region Presenter
    override fun getNewEmployees() {
        view.showLoading()
        repository.getNewEmployees()
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        view.showLoading()
        repository.markAsNew(employeeId, new)
    }
    //endregion

    //regionRepo Listener
    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setNewEmployees(employees)
        view.hideLoading()
    }

    override fun connError() {
        view.hideLoading()
    }

    override fun dataError() {
        view.hideLoading()
    }

    override fun markNewEmployeeSuccess() {
        //view.markNewEmployeeSuccess()
        view.hideLoading()
    }

    override fun markNewEmployeeNotExists(employeeId: Int, new: Boolean) {
        view.hideLoading()
        view.markNewEmployeeNotExists(employeeId)
    }
    //endregion
}