package ru.gb.popularlibrary.mvp_login.data.registration

import ru.gb.popularlibrary.mvp_login.domain.RegistrationApi

class WebRegistrationApiImpl: RegistrationApi {
    override fun addNewUser(login: String, password: String, email: String): Boolean {
        // TODO: Временная заглушка
        return false
    }
}