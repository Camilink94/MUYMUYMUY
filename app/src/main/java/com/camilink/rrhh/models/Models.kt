package com.camilink.rrhh.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.camilink.rrhh.repository.db.EmployeeDatabase
import kotlinx.android.parcel.Parcelize

@Entity(tableName = EmployeeDatabase.employeeTableName)
@Parcelize
data class EmployeeModel(
    @PrimaryKey val id: Int,
    val name: String = "",
    val position: String = "",
    val salary: String = "",
    val phone: String = "",
    val email: String = "",
    @ColumnInfo(name = "upper_relation") val upperRelation: Int = 0,
    val new: Boolean = false
) : Parcelable