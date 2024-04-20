package com.example.xeta.data

import com.example.xeta.data.model.Api
import com.example.xeta.common.Result
import com.example.xeta.data.model.food_info_data.FoodInfoModel
import com.example.xeta.domain.FoodInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FoodInfoRepositoryImpl(
    private val api: Api
):FoodInfoRepository {
    override suspend fun getFoodInfoScreenDetails(): Flow<Result<FoodInfoModel>> {
        return flow{
            val result=try {
                api.getFoodInfoData()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Result.Error(data = null,"Error IOException"))
                return@flow
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error(data = null,message = "Error HttpExcaption"))
                return@flow
            }
            if (!result.success){
                emit(Result.Error(data = null,message =result.message))
            }
            emit(Result.Success(result.data))
        }
    }
}