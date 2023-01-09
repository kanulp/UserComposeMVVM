package com.kanulp.userscompose.bottom_navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.kanulp.userscompose.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        titleResId = R.string.screen_title_home,
        icon = Icons.Default.Home
    )

    object Image : BottomNavItem(
        route = Screen.Image.route,
        titleResId = R.string.screen_title_image,
        icon = Icons.Default.Menu
    )

    object Profile : BottomNavItem(
        route = Screen.Profile.route,
        titleResId = R.string.screen_title_profile,
        icon = Icons.Default.AccountCircle
    )
}