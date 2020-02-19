package pl.patrykzygo.todo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.patrykzygo.todo.Services.AppNotificationChannels

class TodoApplication: Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.alarm_notification_channel)
            val descriptionText = getString(R.string.alarm_channel_description)
            val importanceLevel = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                AppNotificationChannels.ALARM_NOTIFICATION_CHANNEL,
                name,
                importanceLevel
                ).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}