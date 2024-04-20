package com.example.xeta.data.model


import com.example.xeta.data.model.ResultApi
import com.example.xeta.data.model.food_info_data.FoodInfoModel
import com.example.xeta.data.model.home_data.HomePageModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("homepage_v2/")
    suspend fun getHomeData(@Query("format") json:String): ResultApi<HomePageModel>

    @GET("food_info/?format=json")
    suspend fun getFoodInfoData(): ResultApi<FoodInfoModel>


    companion object{
        const val BASE_URL="http://52.25.229.242:8000/"
    }

}
