package com.erendagstan.storeapp_v1

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val sharedViewModel : SharedViewModel = viewModel()
    val storeAPIService : StoreAPIService = StoreAPIService()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController,sharedViewModel, storeAPIService =storeAPIService )
        }
        composable(Screen.Detail.route) {
            DetailScreen(navController = navController,sharedViewModel)
        }
        composable(route = Screen.Tshirts.route){
            TshirtsScreen(navController = navController,sharedViewModel)
        }
        
    }
}

/*composable(Screen.Detail.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Products>("products")
            if (result != null) {
                DetailScreen(navController = navController, result,sharedViewModel)
            }
        }*/

/* composable(route = Screen.Tshirts.route, arguments = listOf(navArgument("id"){
            type = NavType.StringType
        })) {
                val result = it.arguments?.getString("id").toString()
                TshirtsScreen(navController = navController,result,sharedViewModel)

        }*/
