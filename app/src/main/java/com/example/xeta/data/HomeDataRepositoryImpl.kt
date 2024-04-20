package com.example.xeta.data

import android.util.Log
import com.example.xeta.common.Result
import com.example.xeta.data.model.Api
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.domain.HomeDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class HomeDataRepositoryImpl(
    private val api: Api
) : HomeDataRepository {
    override suspend fun getHomeScreenDetails(): Flow<Result<HomePageModel>> {
        return flow {

            val result = try {
                api.getHomeData("json")
            } catch (e: IOException) {
                println("Excaption2")

                e.printStackTrace()
                emit(Result.Error(data = null, "Error IOException"))
                return@flow
            } catch (e: HttpException) {
                println("Excaption3")

                e.printStackTrace()
                emit(Result.Error(data = null, message = "Error HttpExcaption"))
                return@flow
            } catch (e: Exception) {
                println("Excaption4")

                e.printStackTrace()
                emit(Result.Error(data = null, message = "Some Error Occured"))
                return@flow
            }
            emit(Result.Success(result.data))
        }
    }
}