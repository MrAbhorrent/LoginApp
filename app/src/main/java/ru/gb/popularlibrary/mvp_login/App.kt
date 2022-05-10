package ru.gb.popularlibrary.mvp_login

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import ru.gb.popularlibrary.mvp_login.data.LoginUsecaseImpl
import ru.gb.popularlibrary.mvp_login.data.RegistrationUsecaseImpl
import ru.gb.popularlibrary.mvp_login.data.loginapi.MockLoginApiImpl
import ru.gb.popularlibrary.mvp_login.data.registration.MockRegistrationApiImpl
import ru.gb.popularlibrary.mvp_login.domain.LoginApi
import ru.gb.popularlibrary.mvp_login.domain.LoginUsecase
import ru.gb.popularlibrary.mvp_login.domain.RegistrationApi
import ru.gb.popularlibrary.mvp_login.domain.RegistrationUsecase

class App: Application() {

    private val requestAPI : LoginApi by lazy { MockLoginApiImpl() }
    private val registrationApi: RegistrationApi by lazy { MockRegistrationApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.requestAPI, Handler(Looper.getMainLooper()))
    }
    val registrationUsecase: RegistrationUsecase by lazy {
        RegistrationUsecaseImpl(app.registrationApi, Handler(Looper.getMainLooper()))
    }

}

val Context.app: App
    get() {
         return applicationContext as App
    }
