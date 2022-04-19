package ru.gb.popularlibrary.mvp_login

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import ru.gb.popularlibrary.mvp_login.data.LoginUsecaseImpl
import ru.gb.popularlibrary.mvp_login.data.MockLoginApiImpl
import ru.gb.popularlibrary.mvp_login.domain.LoginApi
import ru.gb.popularlibrary.mvp_login.domain.LoginUsecase

class App: Application() {

    private val requestAPI : LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy {
        LoginUsecaseImpl(app.requestAPI, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
         return applicationContext as App
    }
