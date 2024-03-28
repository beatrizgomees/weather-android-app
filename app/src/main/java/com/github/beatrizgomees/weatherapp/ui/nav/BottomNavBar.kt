package com.github.beatrizgomees.weatherapp.ui.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.beatrizgomees.weatherapp.ui.nav.ui.theme.WeatherAppTheme



@Composable
fun BottomNavBar(navController: NavHostController) {
   val items = listOf(
       BottomNavItem.HomePage,
       BottomNavItem.ListPage,
       BottomNavItem.MapPage
   )

    NavigationBar(contentColor = Color.Black) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{ item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route){
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it)
                    }
                    launchSingleTop = true
                }


                          },
                icon = {Icon(imageVector = item.icon, contentDescription = item.title)},
                label = {Text(text = item.title, fontSize = 12.sp)},
                alwaysShowLabel = true
                )
        }
    }
}
