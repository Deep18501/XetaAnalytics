package com.example.xeta.di

import com.example.chatwiseassignment.data.RetrofitInstance
import com.example.xeta.data.model.Api
import com.example.xeta.data.FoodInfoRepositoryImpl
import com.example.xeta.data.HomeDataRepositoryImpl
import com.example.xeta.domain.FoodInfoRepository
import com.example.xeta.domain.HomeDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return RetrofitInstance.api
    }


    @Provides
    @Singleton
    fun providesHomeRepository(api: Api):HomeDataRepository{
        return HomeDataRepositoryImpl(api)
    }



    @Provides
    @Singleton
    fun providesFoodInfoRepository(api: Api):FoodInfoRepository{
        return FoodInfoRepositoryImpl(api)
    }

}