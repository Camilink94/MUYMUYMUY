package com.camilink.rrhh.repository.service

import retrofit2.Call
import retrofit2.http.GET

interface EmployeeServiceContract {

    @GET("/slozanomuy/Services/master/RH.json")
    fun getEmployees(): Call<String>

}