package com.rikaimind.appexercise.ui.feature.detail


import com.rikaimind.appexercise.data.api.model.UserDetail


class UserDetailsContract {
    data class State(
        val userDetail: UserDetail?,
    )
}