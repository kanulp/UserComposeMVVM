package com.kanulp.userscompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.kanulp.userscompose.util.UserPref
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val dataStore = UserPref(context)
    val scope = rememberCoroutineScope()
    val user = dataStore.getUser.collectAsState(initial = "")
    Column {
        Text(text = "User : ${user.value}")
        Button(onClick = {
            scope.launch{
                dataStore.saveUser("")
                dataStore.savePassword("")
            }
        }) {
            Text(text = "Sign Out")
        }
    }


}