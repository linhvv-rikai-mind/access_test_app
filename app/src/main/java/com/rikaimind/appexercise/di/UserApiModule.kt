package com.rikaimind.appexercise.di

import com.rikaimind.appexercise.data.api.ApiConstants
import com.rikaimind.appexercise.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserApiModule {
    @Provides
    @Singleton
    fun provideApi(builder:Retrofit.Builder): UserApi{
        return builder
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }



}