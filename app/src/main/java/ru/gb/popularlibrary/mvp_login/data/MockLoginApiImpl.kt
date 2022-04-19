package ru.gb.popularlibrary.mvp_login.data

import ru.gb.popularlibrary.mvp_login.domain.LoginApi

class MockLoginApiImpl: LoginApi {

    private val TIME_WAIT_CONST = 3_000L

    override fun login(login: String, password: String): Boolean {
        Thread.sleep(TIME_WAIT_CONST)
        return login == password
    }

    override fun register(login: String, password: String, email: String): Boolean {
        Thread.sleep(TIME_WAIT_CONST)
        return login.isNotEmpty()
    }

    override fun logout(): Boolean {
        Thread.sleep(TIME_WAIT_CONST)
        return true
    }

    override fun restorePassword(login: String): Boolean {
        Thread.sleep(TIME_WAIT_CONST)
        return false
    }
}