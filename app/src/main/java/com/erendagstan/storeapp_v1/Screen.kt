package com.erendagstan.storeapp_v1

sealed class Screen(val route : String){
    object Home : Screen("home_screen")
    object Detail: Screen("detail_screen")
    object Tshirts: Screen("tshirts_screen")
}
