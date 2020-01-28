package com.camilink.rrhh.repository.db

import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.util.ListOrder
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class EmployeeDatabaseEntryPoint : KoinComponent {

    private val roomDatabase: EmployeeRoomDatabase by inject()

    fun insertAll(employees: ArrayList<EmployeeModel>) =
        roomDatabase.employeeDAO().insertAll(employees)

    fun insertSingleEmployee(employee: EmployeeModel) =
        roomDatabase.employeeDAO().insertSingle(employee)

    fun updateSingleEmployee(employee: EmployeeModel) =
        roomDatabase.employeeDAO().updateSingle(employee)

    fun getEmployee(employeeId: Int) = roomDatabase.employeeDAO().getSingle(employeeId)

    fun getAllEmployees(order: ListOrder = ListOrder.NONE): List<EmployeeModel> = when (order) {
        ListOrder.NONE -> roomDatabase.employeeDAO().getAll()
        ListOrder.ASCENDING -> roomDatabase.employeeDAO().getAllAsc()
        ListOrder.DESCENDING -> roomDatabase.employeeDAO().getAllDesc()
    }


    fun getFiltered(query: String) = roomDatabase.employeeDAO().getFilter("%$query%")

    fun getRespondingEmployees(employeeId: Int) =
        roomDatabase.employeeDAO().getRespondingEmployees(employeeId)

    fun getNewEmployees() = roomDatabase.employeeDAO().getNewEmployees()

}