package ru.gb.popularlibrary.mvp_login.data

import ru.gb.popularlibrary.mvp_login.domain.LoginApi

class WebLoginApiImpl: LoginApi {

    private val TIME_WAIT_CONST = 3_000L

    override fun login(login: String, password: String): Boolean {
        // TODO: Need make network request implementation
        Thread.sleep(TIME_WAIT_CONST)
        return login == password
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
        return true
    }

    override fun restorePassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}