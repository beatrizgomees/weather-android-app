package com.github.beatrizgomees.weatherapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextCustom(label: String,
                    modifier: Modifier = Modifier,
                    onValueChange: (String) -> Unit,
                    value: String,
                    isPassword: Boolean = false
) {

    Column(modifier = modifier.padding(4.dp)) {
        OutlinedTextField(
            value = value,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

