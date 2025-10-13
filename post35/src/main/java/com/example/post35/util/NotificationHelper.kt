package com.example.post35.util

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.post35.util.NotificationSender

class NotificationHelper(private val context: ComponentActivity) {

    private var handler: () -> Unit = {}
    private val sender = NotificationSender(context)

    private val permissionHandler = context.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted)
            handler.invoke()
    }

    private val settingsLauncher = context.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (hasScheduleAlarmPermission)
            handler.invoke()
    }

    private val hasNotificationPermission: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else true

    private val hasScheduleAlarmPermission: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.canScheduleExactAlarms()
        } else true

    fun handleSimpleNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            handler = { sender.showSimpleNotification() }
            if (hasNotificationPermission) {
                sender.showSimpleNotification()
            } else {
                permissionHandler.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            sender.showSimpleNotification()
        }
    }

    fun tryStartRepeatingNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasNotificationPermission) {
                permissionHandler.launch(Manifest.permission.POST_NOTIFICATIONS)

                return
            }

            if (!hasScheduleAlarmPermission) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    data = Uri.fromParts(
                        "package",
                        context.packageName,
                        null
                    )
                }

                settingsLauncher.launch(intent)

                return
            }

            sender.startRepeatingNotifications()
        } else {
            sender.startRepeatingNotifications()
        }
    }

    fun stopRepeatingNotifications() = sender.stopRepeatingNotifications()
}