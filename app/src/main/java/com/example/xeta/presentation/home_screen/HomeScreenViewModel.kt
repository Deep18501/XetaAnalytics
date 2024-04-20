package com.example.xeta.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xeta.common.Result
import com.example.xeta.common.UiEvents
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.domain.HomeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeDataRepository
) :ViewModel(){
    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()
    var homeData = MutableStateFlow<HomePageModel>(HomePageModel(null,null,null,null))

    init {
        getHomeData()
    }
    fun getHomeData(){
        viewModelScope.launch {
            try {
                repository.getHomeScreenDetails().collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _eventFlow.emit(UiEvents.SnackbarEvent((result.message + " displaying default data") ?: "Error"))
                        }

                        is Result.Success -> {
                            _eventFlow.emit(UiEvents.SnackbarEvent("Data Loaded"))
                            result.data?.let { homeData.emit(it) }
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