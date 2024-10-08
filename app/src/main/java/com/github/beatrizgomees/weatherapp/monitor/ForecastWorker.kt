package com.github.beatrizgomees.weatherapp.monitor

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.beatrizgomees.weatherapp.R
import com.github.beatrizgomees.weatherapp.activitys.MainActivity

class ForecastWorker(context: Context, params: WorkerParameters) : Worker(context,
    params) {
    companion object {
        private const val CHANNEL_ID: String = "WEATHER_APP"
    }
    override fun doWork(): Result {
        val cityName = inputData.getString("city") ?: return Result.failure()
        showNotification(cityName)
        return Result.success()
    }
    private fun showNotification(cityName: String) {
        val newIntent = Intent(this.applicationContext,
            MainActivity::class.java)
        newIntent.addFlags(
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.putExtra("city", cityName)
        val pendingIntent = PendingIntent.getActivity(
            this.applicationContext, cityName.hashCode(), newIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        createNotificationChannel()
        val builder = NotificationCompat
            .Builder(this.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(cityName)
            .setContentText("Clique para ver previsão do tempo atualizada.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager: NotificationManager =
            this.applicationContext
                .getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            // ID = hashCode: para substituir ou remover notificações
        notificationManager.notify(cityName.hashCode(), builder.build())
    }
    private fun createNotificationChannel() {
        val name = "WeatherApp"
        val descriptionText = "WeatherApp Notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
            .apply {
                description = descriptionText
            }
        val notificationManager: NotificationManager = this.applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}