package com.kanulp.userscompose.data.remote

import com.kanulp.userscompose.data.models.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("/api/users?page=1")
    suspend fun getUsers(): Response<UserModel>
}