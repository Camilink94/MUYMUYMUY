package com.camilink.rrhh.di

import androidx.room.Room
import com.camilink.rrhh.presenter.AllEmployeeListPresenter
import com.camilink.rrhh.presenter.contract.AllEmployeesListPresenterContract
import com.camilink.rrhh.repository.EmployeeRepository
import com.camilink.rrhh.repository.db.EmployeeRoomDatabase
import com.camilink.rrhh.repository.service.EmployeeService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    //Presenter
    factory<AllEmployeesListPresenterContract.IPresenter> { (view: AllEmployeesListPresenterContract.IView) ->
        AllEmployeeListPresenter(view)
    }

    //Repository
    factory { (listener: EmployeeRepository.Listener) -> EmployeeRepository(listener) }

    //Room
    single {
        Room.databaseBuilder(
            androidContext(),
            EmployeeRoomDatabase::class.java,
            EmployeeRoomDatabase.employeeDatabaseName
        ).build()
    }

    //Service
    single { (listener: EmployeeService.Listener) -> EmployeeService(listener) }

}