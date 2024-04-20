package com.example.xeta.presentation.food_info_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xeta.common.Result
import com.example.xeta.common.UiEvents
import com.example.xeta.data.model.food_info_data.FoodInfoModel
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.domain.FoodInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodScreenViewModel @Inject constructor(
    val repository: FoodInfoRepository
):ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    val foodScreenData: MutableStateFlow<FoodInfoModel?> = MutableStateFlow(null)



    init {
        getFoodScreenData()
    }
    fun getFoodScreenData(){
        viewModelScope.launch {
            try {
                repository.getFoodInfoScreenDetails().collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _eventFlow.emit(UiEvents.SnackbarEvent((result.message + " displaying default data") ?: "Error"))
                        }

                        is Result.Success -> {
                            _eventFlow.emit(UiEvents.SnackbarEvent("Data Loaded"))
                            println("Got Res"+result.data)
                            result.data?.let { foodScreenData?.emit(it) }
                        }
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
                _eventFlow.emit(UiEvents.SnackbarEvent("Some unknown error"))
            }
        }
    }

}