package com.github.beatrizgomees.weatherapp.activitys

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.beatrizgomees.weatherapp.activitys.ui.theme.WeatherAppTheme
import com.github.beatrizgomees.weatherapp.model.MainViewModel
import com.github.beatrizgomees.weatherapp.ui.CityDialog
import com.github.beatrizgomees.weatherapp.ui.nav.BottomNavBar
import com.github.beatrizgomees.weatherapp.ui.nav.BottomNavItem
import com.github.beatrizgomees.weatherapp.ui.nav.MainNavHost
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import androidx.compose.material3.FloatingActionButton as FloatingActionButton1



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val context = LocalContext.current
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState()
            val showButton = currentRoute.value?.destination?.route !=
                    BottomNavItem.MapPage.route
            val launcher = rememberLauncherForActivityResult(contract =
            ActivityResultContracts.RequestPermission(), onResult = {} )

            var showDialog by remember { mutableStateOf(false) }

            // Passar o ViewModel para o MainNavHost
            MainNavHost(navController, mainViewModel, context)
            WeatherAppTheme {
                if (showDialog) CityDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = { city ->
                        if (city.isNotBlank()){
                            mainViewModel.add(city)
                        }
                        showDialog = false
                    })
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Welcome!!") },
                            actions = {
                                IconButton( onClick = { Firebase.auth.signOut()
                                    finish() } ) {
                                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Exit")


                                }
                            }
                        )
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    },
                    floatingActionButton = {
                        if (showButton) {
                            FloatingActionButton1(onClick = { showDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = "Adicionar")
                            }
                        }
                    }
                ) {
                        innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController, mainViewModel, context)
                    }
                }
            }
        }

    }
}
