package com.camilink.rrhh.repository.service

import android.util.Log
import com.camilink.rrhh.BuildConfig
import com.camilink.rrhh.models.EmployeeModel
import com.camilink.rrhh.util.ListOrder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class EmployeeService(private val listener: Listener) {

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com/")
        .build()

    private val employeeService = retrofit.create(EmployeeServiceContract::class.java)

    fun getEmployees(order: ListOrder = ListOrder.NONE) {
        employeeService.getEmployees().enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if (response.isSuccessful)
                        try {
                            listener.gotEmployees(
                                EmployeeMapper.mapEmployees(
                                    response.body() ?: ""
                                ),
                                order
                            )
                        } catch (ex: EmployeeMapper.EmployeeMapperException) {
                            listener.dataError()
                        }
                    else {
                        Log.d("EmployeeService", "Connection error. Code ${response.code()}")
                        listener.connError()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    listener.connError()
                }
            }
        )
    }

    interface Listener {
        fun gotEmployees(
            employeesFromService: ArrayList<EmployeeModel>,
            order: ListOrder = ListOrder.NONE
        )

        fun connError()
        fun dataError()
    }

}