package com.example.acerestaurantmenucompose

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object CalorieCounterScreen : Screen("calorie_counter_screen")
    object ApiTestScreen : Screen("api_test_screen")
}