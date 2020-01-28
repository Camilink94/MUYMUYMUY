package com.camilink.rrhh.repository.db

import androidx.room.*
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.util.ListOrder

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName}")
    fun getAll(): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} ORDER BY ${EmployeeModel.salaryColumnName} ASC")
    fun getAllAsc(): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} ORDER BY ${EmployeeModel.salaryColumnName} DESC")
    fun getAllDesc(): List<EmployeeModel>


    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.nameColumnName} LIKE :query")
    fun getFilter(query: String): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.nameColumnName} LIKE :query ORDER BY ${EmployeeModel.salaryColumnName} ASC")
    fun getFilterAsc(query: String): List<EmployeeModel>

    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.nameColumnName} LIKE :query ORDER BY ${EmployeeModel.salaryColumnName} DESC")
    fun getFilterDesc(query: String): List<EmployeeModel>


    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.idColumnName} = :employeeId")
    fun getSingle(employeeId: Int): EmployeeModel?


    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.newColumnName} = 1")
    fun getNewEmployees(): List<EmployeeModel>


    @Query("SELECT * FROM ${EmployeeRoomDatabase.employeeTableName} WHERE ${EmployeeModel.upperRelationColumnName} = :employeeId")
    fun getRespondingEmployees(employeeId: Int): List<EmployeeModel>


    @Insert
    fun insertSingle(employeeModel: EmployeeModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(employees: ArrayList<EmployeeModel>)


    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSingle(employeeModel: EmployeeModel)
}