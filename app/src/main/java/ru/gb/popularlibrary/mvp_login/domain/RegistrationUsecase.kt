package ru.gb.popularlibrary.mvp_login.domain

import androidx.annotation.MainThread

interface RegistrationUsecase {

    fun registration(
        registrationLogin: String,
        registrationEmail: String,
        registrationPassword: String,
        registrationConfirmPassword: String,
        @MainThread callback: (Boolean) -> Unit?
    )
}