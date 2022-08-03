package com.rikaimind.appexercise.data.repository

import com.rikaimind.appexercise.data.api.UserApi
import com.rikaimind.appexercise.data.api.model.User
import com.rikaimind.appexercise.data.api.model.UserDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(
    private val userApi: UserApi
) {
    //get list of users
    suspend fun getUsers(pageSize: Int): List<User> {
        return userApi.getUsers(pageSize = pageSize)
    }

    //get user details
    suspend fun getUserDetail(userName : String): UserDetail {
        return userApi.getDetailUser(username = userName)
    }
}