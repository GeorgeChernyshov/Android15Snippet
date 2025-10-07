package com.example.post35.service

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.post35.Post35Application
import com.example.post35.R

class NotificationSender(private val context: Context) {

    fun showSimpleNotification() {
        showNotification(
            title = context.getString(R.string.notification_title),
            text = context.getString(R.string.notification_text)
        )
    }

    @SuppressLint("MissingPermission")
    fun showNotification(title: String, text: String) {
        val builder = NotificationCompat
            .Builder(context, Post35Application.NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId++, builder.build())
        }
    }

    fun startRepeatingNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) { // Android 12+ requires SCHEDULE_EXACT_ALARM
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms())
                return
        }

        stopRepeatingNotifications()
        scheduleNextNotification(SystemClock.elapsedRealtime() + NOTIFICATION_INTERVAL_MILLIS)
    }

    fun stopRepeatingNotifications() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = getRepeatingNotificationPendingIntent()

        alarmManager.cancel(pendingIntent)
    }

    fun getRepeatingNotificationPendingIntent(): PendingIntent {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_SHOW_NOTIFICATION
            putExtra(
                "NOTIFICATION_TITLE",
                context.getString(R.string.notification_title_scheduled)
            )

            putExtra(
                "NOTIFICATION_TEXT",
                context.getString(R.string.notification_text_scheduled) + " (${notificationId})"
            )
        }

        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_REPEATING_ALARM,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun scheduleNextNotification(triggerAtMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = getRepeatingNotificationPendingIntent()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerAtMillis,
            pendingIntent
        )
    }

    companion object {
        var notificationId = 0
        const val NOTIFICATION_INTERVAL_MILLIS = 60 * 1000L // 1 minute
        const val REQUEST_CODE_REPEATING_ALARM = 1259
    }
}