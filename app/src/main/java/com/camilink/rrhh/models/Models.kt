package com.camilink.rrhh.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.camilink.rrhh.repository.db.EmployeeRoomDatabase
import kotlinx.android.parcel.Parcelize

@Entity(tableName = EmployeeRoomDatabase.employeeTableName)
@Parcelize
data class EmployeeModel(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var name: String = "",
    var position: String = "",
    var salary: String = "",
    var phone: String = "",
    var email: String = "",
    @ColumnInfo(name = "upper_relation") var upperRelation: Int = 0,
    var new: Boolean = false
) : Parcelable