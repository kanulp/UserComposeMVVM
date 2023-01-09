package com.kanulp.userscompose.bottom_navigation

sealed class Screen(val route:String){
    object Home : Screen("home")
    object Image : Screen("image")
    object Profile : Screen("profile")
}