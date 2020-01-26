package com.camilink.rrhh.di

import com.camilink.rrhh.presenter.EmployeePresenter
import com.camilink.rrhh.presenter.EmployeePresenterContract
import org.koin.dsl.module

val appModule = module {

    factory { (view: EmployeePresenterContract.IView) -> EmployeePresenter(view) as EmployeePresenterContract.IPresenter }

}