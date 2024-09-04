package com.github.beatrizgomees.weatherapp.activitys

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.beatrizgomees.weatherapp.activitys.ui.theme.WeatherAppTheme
import com.github.beatrizgomees.weatherapp.pages.LoginPage
import com.github.beatrizgomees.weatherapp.viewModel.MainViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelBase = MainViewModel()
            val activity = this
            WeatherAppTheme {
                if (viewModelBase.loggedIn) {
                    Toast.makeText(activity, "LoginToast!", Toast.LENGTH_SHORT).show()
                    activity.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                }
                WeatherAppTheme {
                    LoginPage()
                }
            }
        }
    }
}
