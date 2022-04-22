package ru.gb.popularlibrary.mvp_login.model

open class LoginModel {

    fun getDataAuth(login: String, password: String): Boolean = login == password

}