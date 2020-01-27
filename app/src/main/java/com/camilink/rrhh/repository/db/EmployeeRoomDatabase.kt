package com.camilink.rrhh.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.camilink.rrhh.models.EmployeeModel

@Database(entities = [EmployeeModel::class], version = 1)
abstract class EmployeeRoomDatabase : RoomDatabase() {

    abstract fun employeeDAO(): EmployeeDAO

    companion object {
        const val employeeTableName = "employees"
        const val employeeDatabaseName = "employees-db"
    }
}