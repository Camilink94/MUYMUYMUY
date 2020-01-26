package com.camilink.rrhh.di

import com.camilink.rrhh.presenter.EmployeePresenter
import com.camilink.rrhh.presenter.EmployeePresenterContract
import com.camilink.rrhh.repository.EmployeeRepository
import com.camilink.rrhh.repository.service.EmployeeService
import org.koin.dsl.module

val appModule = module {

    //Presenter
    factory<EmployeePresenterContract.IPresenter> { (view: EmployeePresenterContract.IView) ->
        EmployeePresenter(view)
    }

    //Repository
    factory { (listener: EmployeeRepository.Listener) -> EmployeeRepository(listener) }

    //Service
    single { (listener: EmployeeService.Listener) -> EmployeeService(listener) }

}