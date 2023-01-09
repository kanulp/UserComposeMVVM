package com.kanulp.userscompose.data.remote

import javax.inject.Inject


class UserRemoteDataSource @Inject constructor(private val userApiApiService : UserApiService):BaseDataSource(){
    suspend fun getUsers() =  getResult { userApiApiService.getUsers() }
}
