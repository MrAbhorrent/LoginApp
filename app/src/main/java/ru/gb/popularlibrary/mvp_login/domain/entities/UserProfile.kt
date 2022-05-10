package ru.gb.popularlibrary.mvp_login.domain.entities

data class UserProfile (
    val id: Int,
    val login: String,
    val email: String,
    val avatarUrl: String
)