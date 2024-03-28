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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.github.beatrizgomees.weatherapp.activitys.ui.theme.WeatherAppTheme
import com.github.beatrizgomees.weatherapp.components.InputTextCustom

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage();
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier){
    var email by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Welcome!!",
            fontSize = 24.sp
        )
      InputTextCustom(label = "Name", onValueChange = {email = it} , value = email)
        Spacer(modifier = Modifier.size(24.dp))

       InputTextCustom(label = "Password", onValueChange = {password = it}, value = password, isPassword = true )

        Row(modifier = modifier){
            Button(onClick = {
                Toast.makeText(activity, "Login Ok!", Toast.LENGTH_LONG).show()
                activity?.startActivity(Intent(activity, MainActivity::class.java).setFlags(
                    FLAG_ACTIVITY_SINGLE_TOP
                ))
            }, enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text("Login")
            }
            Button(onClick = {
                email = ""; password = ""
            }) {
                Text(text = "Clean")
            }
        }

        Button(onClick = {
            activity?.startActivity(Intent(activity, RegisterActivity::class.java).setFlags(
                FLAG_ACTIVITY_SINGLE_TOP
            ))
        }) {
            Text(text = "Registro")
        }
    }
}