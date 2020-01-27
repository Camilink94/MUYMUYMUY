package com.camilink.rrhh.presenter

import android.util.Log
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.presenter.contract.AllEmployeesListPresenterContract
import com.camilink.rrhh.repository.EmployeeRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class AllEmployeeListPresenter(private val view: AllEmployeesListPresenterContract.IView) :
    KoinComponent,
    AllEmployeesListPresenterContract.IPresenter,
    EmployeeRepository.Listener {

    private val repository by inject<EmployeeRepository> { parametersOf(this) }

    override fun getAllEmployees() {
        view.showLoading()
        repository.getEmployees()
    }

    override fun getLatestEmployees() {
        view.showLoading()
        repository.getLatestEmployees()
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        view.showLoading()
        Log.d("AAAA", "Mark from presenter")
        repository.markAsNew(employeeId, new)
    }
    //endregion

    //region Repository Listener
    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setEmployees(employees)
        view.hideLoading()
    }

    override fun connError() {
        view.hideLoading()
        view.connError()
    }

    override fun dataError() {
        view.hideLoading()
        view.dataError()
    }

    override fun markNewEmployeeSuccess() {
        view.hideLoading()
        //getAllEmployees()
    }

    override fun markNewEmployeeNotExists(employeeId: Int, new: Boolean) {
        view.hideLoading()
    }
    //endregion

}