package com.github.beatrizgomees.weatherapp.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.beatrizgomees.weatherapp.activitys.ui.theme.WeatherAppTheme
import com.github.beatrizgomees.weatherapp.ui.nav.BottomNavBar
import com.github.beatrizgomees.weatherapp.ui.nav.MainNavHost
import androidx.compose.material3.FloatingActionButton as FloatingActionButton1

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WeatherAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Welcome!!") },
                            actions = {
                                IconButton( onClick = { finish() } ) {
                                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Exit")


                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    },
                    floatingActionButton = {
                        FloatingActionButton1(onClick = { }) {
                            Icon(Icons.Default.Add, contentDescription = "Adicionar")
                        }
                    }
                ) {
                        innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController)
                    }
                }
            }
        }
    }
}

