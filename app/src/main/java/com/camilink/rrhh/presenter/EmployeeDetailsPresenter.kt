package com.camilink.rrhh.presenter

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.EmployeeDetailsPresenterContract
import com.camilink.rrhh.repository.EmployeeRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class EmployeeDetailsPresenter(val view: EmployeeDetailsPresenterContract.IView) :
    KoinComponent,
    EmployeeDetailsPresenterContract.IPresenter,
    EmployeeRepository.Listener {

    private val repository by inject<EmployeeRepository> { parametersOf(this) }

    override fun getRespondingEmployes(employeeId: Int) {
        view.showLoading()
        repository.getRespondingEmployees(employeeId)
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        view.showLoading()
        repository.markAsNew(employeeId, new)
    }

    //region Repo
    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setRespondingEmployees(employees)
        view.hideLoading()
    }

    override fun connError() {
    }

    override fun dataError() {
    }

    override fun markNewEmployeeSuccess() {
        view.hideLoading()
    }

    override fun markNewEmployeeNotExists(employeeId: Int, new: Boolean) {
        view.hideLoading()
    }
    //endregion

}