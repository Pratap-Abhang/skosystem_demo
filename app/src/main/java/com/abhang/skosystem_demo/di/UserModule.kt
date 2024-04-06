package com.abhang.skosystem_demo.di

import com.abhang.skosystem_demo.data.remote.network.NetworkApiService
import com.abhang.skosystem_demo.data.remote.repository.UserDataRepositoryImpl
import com.abhang.skosystem_demo.domain.repository.UserDataRepository
import com.abhang.skosystem_demo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideNetworkAPI(): NetworkApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: NetworkApiService): UserDataRepository {
        return UserDataRepositoryImpl(api)
    }

}