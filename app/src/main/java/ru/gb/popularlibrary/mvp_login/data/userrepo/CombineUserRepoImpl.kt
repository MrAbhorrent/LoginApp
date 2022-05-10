package ru.gb.popularlibrary.mvp_login.data.userrepo

import ru.gb.popularlibrary.mvp_login.domain.UserRepo
import ru.gb.popularlibrary.mvp_login.domain.entities.UserProfile

class CombineUserRepoImpl(
    private val localRepo: UserRepo,
    private val remoteRepo: UserRepo
): UserRepo {

    private var selectRemoteRepo = true

    override fun addUser(user: UserProfile) {
        localRepo.addUser(user)
        remoteRepo.addUser(user)
    }

    override fun getUser(id: Int): UserProfile {

        var userProfile = if (selectRemoteRepo) {
            remoteRepo.getUser(id)
        } else {
            localRepo.getUser(id)
        }
        return userProfile
    }

    override fun getAllUser(): List<UserProfile> {

        var userProfiles: List<UserProfile> = if (selectRemoteRepo) {
            remoteRepo.getAllUser()
        } else {
            localRepo.getAllUser()
        }
        return userProfiles
    }

    override fun changeUser(int: Int, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(int: Int) {
        remoteRepo.deleteUser(int)
        localRepo.deleteUser(int)
    }

    override fun deleteAllUser() {
        remoteRepo.deleteAllUser()
        localRepo.deleteAllUser()
    }
}