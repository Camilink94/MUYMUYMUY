package com.camilink.rrhh.repository.db

import androidx.room.*
import com.camilink.rrhh.models.EmployeeModel

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName}")
    fun getAll(): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.idColumnName} = :employeeId")
    fun getSingle(employeeId: Int): EmployeeModel?

    @Insert
    fun insertSingle(employeeModel: EmployeeModel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSingle(employeeModel: EmployeeModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(employees: ArrayList<EmployeeModel>)
}