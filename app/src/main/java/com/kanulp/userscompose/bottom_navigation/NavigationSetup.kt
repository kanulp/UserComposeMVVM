package com.kanulp.userscompose.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.kanulp.userscompose.screens.HomeScreen
import com.kanulp.userscompose.screens.ImageScreen
import com.kanulp.userscompose.screens.ProfileScreen

@Composable
fun NavigationSetup(navController: NavHostController) {

    NavHost(navController, startDestination = BottomNavItem.Home.route){
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Image.route) {
            ImageScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}