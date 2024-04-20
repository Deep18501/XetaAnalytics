package com.example.xeta.domain

import com.example.xeta.common.Result
import com.example.xeta.data.model.home_data.HomePageModel
import kotlinx.coroutines.flow.Flow

interface HomeDataRepository {
    suspend fun getHomeScreenDetails(): Flow<Result<HomePageModel>>
}