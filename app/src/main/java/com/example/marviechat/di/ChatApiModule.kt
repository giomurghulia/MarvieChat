package com.example.marviechat.di

import com.example.marviechat.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatApiModule {

    @Provides
    @Singleton
    fun provideChatApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}