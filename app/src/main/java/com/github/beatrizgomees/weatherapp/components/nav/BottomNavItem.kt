package com.github.beatrizgomees.weatherapp.components.nav


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed  class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object HomePage : BottomNavItem("Inicio", Icons.Default.Home, "home")
    object ListPage : BottomNavItem("Lista", Icons.Default.Favorite, "list")
    object MapPage : BottomNavItem("Mapa", Icons.Default.LocationOn, "map")
}