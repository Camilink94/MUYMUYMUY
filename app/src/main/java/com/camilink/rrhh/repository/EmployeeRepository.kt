package com.camilink.rrhh.repository

import android.util.Log
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.repository.db.EmployeeDatabaseEntryPoint
import com.camilink.rrhh.repository.service.EmployeeService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class EmployeeRepository(private val listener: Listener) :
    KoinComponent,
    EmployeeService.Listener {

    private val service by inject<EmployeeService> { parametersOf(this) }
    private val database = EmployeeDatabaseEntryPoint()

    fun getLatestEmployees() {
        service.getEmployees()
    }

    fun markAsNew(employeeId: Int, new: Boolean) {
        doAsync {
            val cacheEmployee = database.getEmployee(employeeId)
            cacheEmployee?.let {
                it.isNew = new
                database.updateSingleEmployee(it)
            }
            uiThread {
                if (cacheEmployee == null) {
                    listener.markNewEmployeeNotExists()
                } else {
                    listener.markNewEmployeeSuccess()
                }
            }
        }
    }

    //region Service Listener
    override fun gotEmployees(employeesFromService: ArrayList<EmployeeModel>) {
        doAsync {
            for (employeeFromService in employeesFromService) {
                val cacheEmployee = database.getEmployee(employeeFromService.id)
                if (cacheEmployee == null) {
                    //If null, it doesn't exist, insert new
                    database.insertSingleEmployee(employeeFromService)
                } else {
                    //Not null, already exist. Update
                    database.updateSingleEmployee(
                        employeeFromService.apply {
                            //Keep new state from cached row
                            isNew = cacheEmployee.isNew
                        }
                    )
                }
            }

            val allEmployees = database.getAllEmployees()
            uiThread {
                listener.gotEmployees(allEmployees as ArrayList<EmployeeModel>)
            }
        }
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

        fun markNewEmployeeSuccess()
        fun markNewEmployeeNotExists()
    }

}