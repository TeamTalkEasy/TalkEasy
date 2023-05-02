package com.ssafy.talkeasy.core.data.di

import com.ssafy.talkeasy.core.data.common.util.NoAuthInterceptorClient
import com.ssafy.talkeasy.core.data.remote.service.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @NoAuthInterceptorClient
        retrofit: Retrofit,
    ): AuthApiService = retrofit.create(AuthApiService::class.java)
}