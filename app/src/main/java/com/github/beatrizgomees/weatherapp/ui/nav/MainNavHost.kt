package com.github.beatrizgomees.weatherapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.beatrizgomees.weatherapp.pages.HomePage
import com.github.beatrizgomees.weatherapp.pages.ListPage
import com.github.beatrizgomees.weatherapp.pages.MapPage


@Composable
fun MainNavHost(navController: NavHostController) {
 NavHost(navController, startDestination = BottomNavItem.HomePage.route ){
     composable(route = BottomNavItem.HomePage.route){
         HomePage()
     }
     composable(route = BottomNavItem.ListPage.route){
         ListPage()
     }
     composable(route = BottomNavItem.MapPage.route){
         MapPage()
        }
    }
}

