package com.example.xeta

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatwiseassignment.data.RetrofitInstance
import com.example.xeta.data.model.ResultApi
import com.example.xeta.data.model.home_data.HomePageModel
import com.example.xeta.presentation.Screens
import com.example.xeta.presentation.food_info_screen.FoodInfoScreen
import com.example.xeta.presentation.food_info_screen.FoodScreenViewModel
import com.example.xeta.presentation.home_screen.HomeScreen
import com.example.xeta.presentation.home_screen.HomeScreenViewModel
import com.example.xeta.ui.theme.XetaTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val navController= rememberNavController()
                    
                    NavHost(navController = navController, startDestination = Screens.HomeScreen.route){
                        composable(Screens.HomeScreen.route){
                            val viewModel = hiltViewModel<HomeScreenViewModel>()

                            HomeScreen(navController,viewModel)
                        }
                        composable(Screens.FoodInfoScreen.route){
                            val viewModel2 = hiltViewModel<FoodScreenViewModel>()

                            FoodInfoScreen(navController,viewModel2)
                        }
                    }
                }
            }
        }
    }
}

