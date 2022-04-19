package ru.gb.popularlibrary.mvp_login.domain

import androidx.annotation.MainThread
import javax.security.auth.callback.Callback

interface LoginUsecase {

    fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit?
    )
}