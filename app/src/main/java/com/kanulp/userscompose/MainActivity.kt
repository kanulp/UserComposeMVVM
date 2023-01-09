package com.kanulp.userscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kanulp.userscompose.bottom_navigation.BottomNavigationBar
import com.kanulp.userscompose.bottom_navigation.NavigationSetup
import com.kanulp.userscompose.screens.LoginPage
import com.kanulp.userscompose.ui.theme.UsersComposeTheme
import com.kanulp.userscompose.util.UserPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsersComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    ScreenMain(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ScreenMain(navController: NavHostController) {
    val context = LocalContext.current
    val dataStore = UserPref(context)
    val user = dataStore.getUser.collectAsState(initial = "n/a")
    when (user.value) {
        "n/a" -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
        "" -> {
            LoginPage()
        }
        else -> {
            Scaffold(bottomBar = { BottomNavigationBar(navController) },
                topBar = { TopAppBar(title = { Text("Users") }) },
                content = { NavigationSetup(navController = navController) })
        }
    }
}