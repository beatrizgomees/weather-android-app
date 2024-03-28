package com.github.beatrizgomees.weatherapp.pages

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.beatrizgomees.weatherapp.activitys.MainActivity
import com.github.beatrizgomees.weatherapp.activitys.RegisterActivity
import com.github.beatrizgomees.weatherapp.components.InputTextCustom

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome!!",
            fontSize = 24.sp
        )
        InputTextCustom(label = "Name", onValueChange = { email = it }, value = email)
        Spacer(modifier = Modifier.size(24.dp))

        InputTextCustom(
            label = "Password",
            onValueChange = { password = it },
            value = password,
            isPassword = true
        )

        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Login Ok!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
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
            activity?.startActivity(
                Intent(activity, RegisterActivity::class.java).setFlags(
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                )
            )
        }) {
            Text(text = "Registro")
        }
    }
}