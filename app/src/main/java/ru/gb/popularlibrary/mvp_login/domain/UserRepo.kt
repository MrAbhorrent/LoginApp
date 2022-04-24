package ru.gb.popularlibrary.mvp_login.domain

import ru.gb.popularlibrary.mvp_login.domain.entities.UserProfile

// CRUD
interface UserRepo {

    // Create
    fun addUser(user: UserProfile)

    // Read
    fun getUser(id: Int): UserProfile
    fun getAllUser(): List<UserProfile>

    // Update
    fun changeUser(int: Int, user: UserProfile)

    // Delete
    fun deleteUser(int: Int)
    fun deleteAllUser()
}