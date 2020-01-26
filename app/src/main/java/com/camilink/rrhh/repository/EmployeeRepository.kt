package com.camilink.rrhh.repository

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.repository.service.EmployeeService
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class EmployeeRepository(private val listener: Listener) : KoinComponent, EmployeeService.Listener {

    private val service by inject<EmployeeService> { parametersOf(this) }

    fun getLatestEmployees() {
        service.getEmployees()
    }

    //region Service Listener
    override fun gotEmployees(employees: ArrayList<EmployeeModel>) {
        listener.gotEmployees(employees)
    }

    override fun connError() {
        listener.connError()
    }

    override fun dataError() {
        listener.dataError()
    }
    //endregion

    interface Listener {
        fun gotEmployees(employees: ArrayList<EmployeeModel>)
        fun connError()
        fun dataError()
    }

}