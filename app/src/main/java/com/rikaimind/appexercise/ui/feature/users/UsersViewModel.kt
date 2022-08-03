package com.rikaimind.appexercise.ui.feature.users


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rikaimind.appexercise.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel@Inject constructor(
    private val userRepo: UserRepo
) : ViewModel() {

    var state by mutableStateOf(
        UsersContract.State(
            users = listOf(),
            isLoading = true
        )
    )

    init {
        viewModelScope.launch {
            //get user list limited to 100 users
            val users = userRepo.getUsers(100)
            //update list user
            state = state.copy(users = users, isLoading = false)
        }
    }


}