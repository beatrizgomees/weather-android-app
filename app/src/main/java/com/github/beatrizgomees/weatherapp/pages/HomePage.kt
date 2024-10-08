package com.github.beatrizgomees.weatherapp.pages

import android.content.Context
import android.icu.text.DecimalFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.beatrizgomees.weatherapp.R
import com.github.beatrizgomees.weatherapp.model.Forecast
import com.github.beatrizgomees.weatherapp.repo.Repository
import com.github.beatrizgomees.weatherapp.viewModel.MainViewModel

//@Preview(showBackground = true)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context,
    repo : Repository,
    isMonitoredState: MutableState<Boolean>
) {
    val isMonitored = viewModel.city?.isMonitored ?: false
    // Definir o ícone com base no estado de monitoramento
    val icon: ImageVector = if (isMonitored == true) {
        Icons.Outlined.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }
    Column {
        Spacer(modifier = Modifier.size(20.dp))

        Row {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Localized Description",
                modifier = Modifier.size(130.dp)
            )
            val format = DecimalFormat("#.0")

            Column {
                Text(
                    text = viewModel.city?.name ?: "Selecione uma Cidade...",
                    fontSize = 24.sp)
                Spacer(modifier = Modifier.size(10.dp))
                IsMonitored(viewModel = viewModel, repo =  repo, isMonitoredState =  isMonitoredState)
                Text(
                    text = viewModel.city?.weather?.desc?:"...",
                    fontSize = 20.sp)
                Spacer(modifier = Modifier.size(10.dp))
                /*
                Text(
                    text = "Temp: " + viewModel.city?.weather?.temp + "°C",
                    fontSize = 20.sp
                )

                 */
            }
        }
        //        if (viewModel.city == null ||
        //        viewModel.city!!.forecast == null) return
        viewModel.city?.forecast?.let { forecasts ->
            LazyColumn {
                items(forecasts) { forecast ->
                    ForecastItem(
                        forecast = forecast,
                        onClick = { },
                        modifier = modifier
                    )
                }
            }
        }

    }
}

@Composable
fun ForecastItem(
    forecast: Forecast,
    onClick: (Forecast) -> Unit,
    modifier: Modifier = Modifier
) {
    val format = DecimalFormat("#.0")
    val tempMin = format.format(forecast.tempMin)
    val tempMax = format.format(forecast.tempMax)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onClick(forecast) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage( // Substitui o Icon
            model = forecast.imgUrl,
            modifier = Modifier.size(40.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription = "Imagem"
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                modifier = Modifier,
                text = forecast.weather,
                fontSize = 20.sp,
            )
            Row {
                Text(
                    modifier = Modifier,
                    text = forecast.date,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    modifier = Modifier,
                    text = "Min: $tempMin℃",
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    modifier = Modifier,
                    text = "Max: $tempMax℃",
                    fontSize = 14.sp)
            }
        }
    }
}


@Composable
fun IsMonitored(modifier: Modifier = Modifier,
                viewModel: MainViewModel,
                repo: Repository,
                isMonitoredState: MutableState<Boolean>,

                ){

    Button(
        onClick = {
            if (viewModel.city != null) {
                isMonitoredState.value = !isMonitoredState.value

                // Inverter o status de monitoramento
                val updatedCity = viewModel.city!!.copy(isMonitored = !isMonitoredState.value)
                repo.update(updatedCity)  // Atualizar no repositório

            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor =  if (isMonitoredState.value == true) Color.Green else Color.Red,
            contentColor = Color.White  // Cor do texto
        ),
        modifier = Modifier.padding(16.dp) // Espaçamento do botão
    ) {
        Text(text = if (isMonitoredState.value == true) "Stop Monitoring" else "Start Monitoring")
    }

}