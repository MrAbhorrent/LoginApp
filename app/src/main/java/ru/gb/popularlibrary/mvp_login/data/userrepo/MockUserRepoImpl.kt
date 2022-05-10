package ru.gb.popularlibrary.mvp_login.data.userrepo

import ru.gb.popularlibrary.mvp_login.domain.UserRepo
import ru.gb.popularlibrary.mvp_login.domain.entities.UserProfile

class MockUserRepoImpl: UserRepo {
    override fun addUser(user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Int): UserProfile {
        TODO("Not yet implemented")
    }

    override fun getAllUser(): List<UserProfile> {
        TODO("Not yet implemented")
    }

    override fun changeUser(int: Int, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(int: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteAllUser() {
        TODO("Not yet implemented")
    }
}