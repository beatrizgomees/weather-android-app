package com.github.beatrizgomees.weatherapp.pages

import FBDatabase
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.beatrizgomees.weatherapp.activitys.LoginActivity
import com.github.beatrizgomees.weatherapp.components.InputTextCustom
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name  by rememberSaveable { mutableStateOf("") }
    var email  by rememberSaveable { mutableStateOf("") }
    var password  by rememberSaveable { mutableStateOf("") }
    var confirmPassword  by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    val fbDB = remember { FBDatabase() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        Text("Faça sua conta aqui", fontSize = 24.sp)

        InputTextCustom(value = name, label = "Name", onValueChange = { name = it })
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(value = email, label = "Email", onValueChange = { email = it })
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(
            value = password,
            label = "Password",
            onValueChange = { password = it },
            isPassword = true
        )
        Spacer(modifier = Modifier.size(24.dp))
        InputTextCustom(
            value = confirmPassword,
            label = "Repeat password",
            onValueChange = { confirmPassword = it },
            isPassword = true
        )
        Spacer(modifier = Modifier.size(24.dp))

        Row(modifier = modifier) {
            Button(
                onClick = {
                    Firebase.auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity!!) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(activity,
                                    "Registro OK!", Toast.LENGTH_LONG).show()
                                activity.finish()
                            } else {
                                Toast.makeText(activity,
                                    "Registro FALHOU!", Toast.LENGTH_LONG).show()
                            }
                        }
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