package ru.gb.popularlibrary.mvp_login.domain

interface RegistrationApi {

    fun addNewUser(
        login: String,
        password: String,
        email: String
    ): Boolean
}