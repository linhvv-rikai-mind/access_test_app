package com.rikaimind.appexercise.ui.feature.users

import com.rikaimind.appexercise.data.api.model.User


class UsersContract {
    data class State(
        val users: List<User> = listOf(),
        val isLoading: Boolean = false
    )
}