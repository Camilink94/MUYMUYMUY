package com.camilink.rrhh.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeModel(
    @PrimaryKey val id: Int,
    val name: String = "",
    val position: String = "",
    val salary: String = "",
    val phone: String = "",
    val email: String = "",
    @ColumnInfo(name = "upper_relation") val upperRelation: Int = 0,
    val new: Boolean = false
)