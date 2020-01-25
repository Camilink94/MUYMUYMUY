package com.camilink.rrhh.repository.service

import com.camilink.rrhh.models.EmployeeModel
import org.json.JSONObject

class EmployeeMapper {

    companion object {
        fun mapEmployees(jsonString: String): ArrayList<EmployeeModel> {
            val employees: ArrayList<EmployeeModel> = ArrayList()
            try {
                val employeesJson = JSONObject(jsonString)

                val keys = employeesJson.keys()
                for (key in keys) {
                    val singleEmployeeJson = employeesJson.getJSONObject(key)
                    employees.add(
                        EmployeeModel(
                            id = singleEmployeeJson.getInt("id"),
                            name = key,
                            position = singleEmployeeJson.getString("position"),
                            salary = singleEmployeeJson.getString("salary"),
                            phone = singleEmployeeJson.getString("phone"),
                            email = singleEmployeeJson.getString("email"),
                            upperRelation = singleEmployeeJson.getInt("upperRelation")
                        )
                    )
                }

                return employees
            } catch (ex: Exception) {
                ex.printStackTrace()
                throw EmployeeMapperException()
            }
        }
    }

    class EmployeeMapperException : Exception() {

    }
}