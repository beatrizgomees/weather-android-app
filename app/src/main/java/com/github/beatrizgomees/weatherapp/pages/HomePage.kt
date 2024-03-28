package com.github.beatrizgomees.weatherapp.pages

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.beatrizgomees.weatherapp.R

@Preview(showBackground = true)
@Composable
fun HomePage(modifier: Modifier = Modifier){
    val activity = LocalContext.current as? Activity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700)),
    ) {
        Text("Home",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(24.dp))


    }

}