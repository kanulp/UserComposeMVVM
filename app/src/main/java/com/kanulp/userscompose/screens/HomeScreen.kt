package com.kanulp.userscompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.kanulp.userscompose.data.models.Data
import com.kanulp.userscompose.viewmodels.UserViewModel

@Composable
fun HomeScreen(viewModel: UserViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        Modifier.padding(bottom = 55.dp)
        ) {
        if (state.isEmpty()){
            item {
                CircularProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .wrapContentSize(align = Alignment.Center))
            }
        }
        items(state) { user : Data ->
            UserCard(user)
        }
    }
}

@Composable
fun UserCard(user: Data) {
    val imagePainter = rememberAsyncImagePainter(model = user.avatar)
    Card(shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            Image(painter = imagePainter, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
                )
            Surface(color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
                ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Text(text = "${user.first_name} ${user.last_name}")
                    Text(text = "${user.email}")
                }
            }
        }
    }
}
