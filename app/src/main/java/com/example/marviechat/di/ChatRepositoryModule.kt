package com.example.marviechat.di


import com.example.marviechat.data.ChatRepositoryImpl
import com.example.marviechat.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatRepositoryModule {

    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ChatRepositoryImpl): ChatRepository
}