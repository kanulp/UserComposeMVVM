package com.kanulp.userscompose.repository

import com.kanulp.userscompose.data.models.UserModel
import com.kanulp.userscompose.data.remote.UserRemoteDataSource
import com.kanulp.userscompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDatabase: UserRemoteDataSource) {

    suspend fun fetchUser(): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.loading())
            val result = remoteDatabase.getUsers()
            emit(result)

        }.flowOn(Dispatchers.IO)
    }
}