package com.camilink.rrhh.repository

import android.util.Log
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.repository.db.EmployeeDatabaseEntryPoint
import com.camilink.rrhh.repository.service.EmployeeService
import com.camilink.rrhh.util.ListOrder
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

    fun getEmployees(order: ListOrder = ListOrder.NONE) {
        doAsync {
            val dbEmployees = database.getAllEmployees(order)
            uiThread {
                if (dbEmployees.isNotEmpty()) {
                    listener.gotEmployees(dbEmployees as ArrayList<EmployeeModel>)
                } else {
                    getLatestEmployees(order)
                }
            }
        }
    }

    fun getFiltered(query: String, order: ListOrder = ListOrder.NONE) {
        doAsync {
            val filtered = database.getFiltered(query, order)
            uiThread {
                listener.gotEmployees(filtered as ArrayList<EmployeeModel>)
            }
        }
    }

    fun getLatestEmployees(order: ListOrder = ListOrder.NONE) {
        service.getEmployees(order)
    }

    fun getNewEmployees() {
        doAsync {
            val news = database.getNewEmployees()
            uiThread {
                listener.gotEmployees(news as ArrayList<EmployeeModel>)
            }
        }
    }

    fun getRespondingEmployees(employeeId: Int) {
        doAsync {
            val respondingEmployees = database.getRespondingEmployees(employeeId)
            uiThread {
                gotEmployees(respondingEmployees as ArrayList<EmployeeModel>)
            }
        }
    }

    fun markAsNew(employeeId: Int, new: Boolean) {
        doAsync {
            Log.d("AAAA", "Getting $employeeId")
            val cacheEmployee = database.getEmployee(employeeId)
            cacheEmployee?.let {
                Log.d("AAAA", "Got ${it.id}")
                it.isNew = new
                database.updateSingleEmployee(it)
            }
            uiThread {
                if (cacheEmployee == null) {
                    listener.markNewEmployeeNotExists(employeeId, new)
                } else {
                    listener.markNewEmployeeSuccess()
                }
            }
        }
    }

    //region Service Listener
    override fun gotEmployees(
        employeesFromService: ArrayList<EmployeeModel>,
        order: ListOrder
    ) {
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

            val allEmployees = database.getAllEmployees(order)
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
        fun markNewEmployeeNotExists(employeeId: Int, new: Boolean)
    }

}