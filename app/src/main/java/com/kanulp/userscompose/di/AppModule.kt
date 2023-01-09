package com.kanulp.userscompose.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kanulp.userscompose.data.remote.UserApiService
import com.kanulp.userscompose.data.remote.UserRemoteDataSource
import com.kanulp.userscompose.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(apiService: UserApiService) = UserRemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource) =
        UserRepository(remoteDataSource)

}