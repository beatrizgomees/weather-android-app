package com.github.beatrizgomees.weatherapp.ui.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.beatrizgomees.weatherapp.model.MainViewModel
import com.github.beatrizgomees.weatherapp.pages.HomePage
import com.github.beatrizgomees.weatherapp.pages.ListPage
import com.github.beatrizgomees.weatherapp.pages.MapPage

@Composable
fun MainNavHost(navController: NavHostController, viewModel: MainViewModel, context: Context) {
    val viewModel: MainViewModel = viewModel()
 NavHost(navController, startDestination = BottomNavItem.HomePage.route ){
     composable(route = BottomNavItem.HomePage.route){
         HomePage(viewModel = viewModel, context = context)
     }
     composable(route = BottomNavItem.ListPage.route){
         ListPage(viewModel = viewModel, context = context)
     }
     composable(route = BottomNavItem.MapPage.route){
         MapPage(viewModel = viewModel, context = context)
        }
    }
}

