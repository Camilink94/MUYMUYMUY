package com.camilink.rrhh.repository.db

import androidx.room.*
import com.camilink.rrhh.models.EmployeeModel

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName}")
    fun getAll(): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.nameColumnName} LIKE :query")
    fun getFilter(query: String): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.idColumnName} = :employeeId")
    fun getSingle(employeeId: Int): EmployeeModel?

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.newColumnName} = 1")
    fun getNewEmployees(): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.upperRelationColumnName} = :employeeId")
    fun getRespondingEmployees(employeeId: Int): List<EmployeeModel>

    @Insert
    fun insertSingle(employeeModel: EmployeeModel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSingle(employeeModel: EmployeeModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(employees: ArrayList<EmployeeModel>)
}