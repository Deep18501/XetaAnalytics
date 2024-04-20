package com.example.xeta.presentation

sealed class Screens(val route:String) {
    data object HomeScreen:Screens("home_screen")
    data object FoodInfoScreen:Screens("food_screen")
}