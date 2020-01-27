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
        repository.getEmployees()
    }

    override fun getLatestEmployees() {
        repository.getLatestEmployees()
    }

    override fun markNewEmployee(employeeId: Int, new: Boolean) {
        Log.d("AAAA", "Mark from presenter")
        repository.markAsNew(employeeId, new)
    }
    //endregion

    //region Repository Listener
    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        view.setEmployees(employees)
    }

    override fun connError() {
        view.connError()
    }

    override fun dataError() {
        view.dataError()
    }

    override fun markNewEmployeeSuccess() {
        //getAllEmployees()
    }

    override fun markNewEmployeeNotExists(employeeId: Int, new: Boolean) {

    }
    //endregion

}