package com.example.xeta.domain

import com.example.xeta.common.Result
import com.example.xeta.data.model.food_info_data.FoodInfoModel
import kotlinx.coroutines.flow.Flow

interface FoodInfoRepository {

    suspend fun getFoodInfoScreenDetails():Flow<Result<FoodInfoModel>>

}