package ru.gb.popularlibrary.mvp_login.data.registration

import ru.gb.popularlibrary.mvp_login.domain.RegistrationApi

class MockRegistrationApiImpl: RegistrationApi {

    private val TIME_WAIT_CONST = 3_000L

    override fun addNewUser(login: String, password: String, email: String): Boolean {
        Thread.sleep(TIME_WAIT_CONST)
        return login.isNotEmpty() || password.isNotEmpty() || email.isNotEmpty()
    }
}