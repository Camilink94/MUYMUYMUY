package com.camilink.rrhh.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.camilink.rrhh.models.EmployeeModel

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM ${EmployeeDatabase.employeeTableName}")
    fun getAll(): List<EmployeeModel>

    @Insert
    fun insertSingle(employeeModel: EmployeeModel)

    @Insert
    fun insertAll(vararg employees: EmployeeModel)
}