package ru.gb.popularlibrary.mvp_login.data

import android.os.Handler
import ru.gb.popularlibrary.mvp_login.domain.LoginApi
import ru.gb.popularlibrary.mvp_login.domain.RegistrationApi
import ru.gb.popularlibrary.mvp_login.domain.RegistrationUsecase


class RegistrationUsecaseImpl(
    private val api: RegistrationApi,
    private val uiHandler: Handler
) : RegistrationUsecase {

    override fun registration(
        registrationLogin: String,
        registrationEmail: String,
        registrationPassword: String,
        registrationConfirmPassword: String,
        callback: (Boolean) -> Unit?
    ) {
        if (registrationConfirmPassword == registrationPassword) {
            Thread {
                val result = api.addNewUser(registrationLogin, registrationPassword, registrationEmail)
                uiHandler.post {
                    callback(result)
                }
            }.start()
        }
    }

}
