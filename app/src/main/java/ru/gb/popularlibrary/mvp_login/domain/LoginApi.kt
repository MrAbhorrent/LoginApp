package ru.gb.popularlibrary.mvp_login.domain

import androidx.annotation.WorkerThread

interface LoginApi {

    @WorkerThread
    fun login(login: String, password: String): Boolean

    @WorkerThread
    fun register(login: String, password: String, email: String): Boolean

    @WorkerThread
    fun logout(): Boolean

    @WorkerThread
    fun restorePassword(login:String): Boolean
}