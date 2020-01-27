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
    @ColumnInfo(name = upperRelationColumnName) var upperRelation: Int = 0,
    var new: Boolean = false
) : Parcelable {
    companion object {
        const val idColumnName = "id"
        const val upperRelationColumnName = "upper_relation"
    }
}