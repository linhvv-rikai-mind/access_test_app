package com.rikaimind.appexercise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rikaimind.appexercise.ui.NavigationKeys.Arg.USER_NAME
import com.rikaimind.appexercise.ui.feature.detail.UserDetailScreen
import com.rikaimind.appexercise.ui.feature.detail.UserDetailViewModel
import com.rikaimind.appexercise.ui.theme.AppExerciseTheme
import com.rikaimind.appexercise.ui.feature.users.UserListScreen
import com.rikaimind.appexercise.ui.feature.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppExerciseTheme {
                Surface(color = MaterialTheme.colors.background) {
                    UserApp()
                }
            }
        }
    }
}



@Composable
private fun UserApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.USER_LIST) {
        composable(
            route = NavigationKeys.Route.USER_LIST
        ) {
            UsersDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.USER_DETAILS,
            arguments = listOf(navArgument(USER_NAME) {
                type = NavType.StringType
            })
        ) {
            UserDetailsDestination(navController)
        }
    }
}




@Composable
private fun UsersDestination(navController: NavHostController) {
    val viewModel: UsersViewModel = hiltViewModel()
    UserListScreen(
        state = viewModel.state,
        onNavigationRequested = { itemId ->
            navController.navigate("${NavigationKeys.Route.USER_LIST}/${itemId}")//process when click user item
        })
}



@Composable
private fun UserDetailsDestination(navController: NavHostController) {
    val viewModel: UserDetailViewModel = hiltViewModel()
    UserDetailScreen(
        viewModel.state,
        navController
    )
}


object NavigationKeys {

    object Arg {
        const val USER_NAME = "userName"
    }

    object Route {
        const val USER_LIST = "user_list"
        const val USER_DETAILS = "$USER_LIST/{$USER_NAME}"
    }

}

