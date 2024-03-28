package com.github.beatrizgomees.weatherapp.activitys

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.beatrizgomees.weatherapp.activitys.ui.theme.WeatherAppTheme
import com.github.beatrizgomees.weatherapp.components.InputTextCustom

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   RegisterPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name  by rememberSaveable {mutableStateOf("")}
    var email  by rememberSaveable {mutableStateOf("")}
    var password  by rememberSaveable {mutableStateOf("")}
    var confirmPassword  by rememberSaveable {mutableStateOf("")}
    val activity = LocalContext.current as? Activity


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
        ){
        Text("Faça sua conta aqui", fontSize = 24.sp)

        InputTextCustom(value = name ,  label = "Name", onValueChange = {name  = it} )
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(value = email ,  label = "Email", onValueChange = {email  = it} )
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(value = password ,  label = "Password", onValueChange = {password  = it}, isPassword = true )
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(value = confirmPassword ,  label = "Repeat password",  onValueChange = {confirmPassword  = it}, isPassword = true )
        Spacer(modifier = Modifier.size(24.dp))

        Row(modifier = modifier){
            Button(
                onClick = {
                    Toast.makeText(activity, "Registro OK!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(
                            activity, LoginActivity::class.java
                        ).setFlags(FLAG_ACTIVITY_SINGLE_TOP)
                    )
                },
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()

            ) {
                Text("Registrar")
            }
            Spacer(modifier = Modifier.size(24.dp))

            Button(
                onClick = { name = ""; email = ""; password = ""; confirmPassword = "" }
            ) {
                Text("Limpar")
            }

        }
    }
}