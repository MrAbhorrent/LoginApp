package ru.gb.popularlibrary.mvp_login.data

import android.os.Handler
import ru.gb.popularlibrary.mvp_login.domain.LoginApi
import ru.gb.popularlibrary.mvp_login.domain.LoginUsecase


class LoginUsecaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUsecase {
    override fun login(login: String, password: String, callback: (Boolean) -> Unit?) {

        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}