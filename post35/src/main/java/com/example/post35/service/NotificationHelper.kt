package com.example.post35.service

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.post35.Post35Application
import com.example.post35.R

class NotificationHelper(private val context: ComponentActivity) {

    private val permissionHandler = context.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted)
            showSimpleNotification()
    }

    private var handler: () -> Unit = {}

    fun handleSimpleNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            handler = { showSimpleNotification() }
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) { showSimpleNotification() }
            else { permissionHandler.launch(Manifest.permission.POST_NOTIFICATIONS) }
        } else {
            showSimpleNotification()
        }
    }

    private fun showSimpleNotification() {
        val builder = NotificationCompat
            .Builder(context, Post35Application.NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_MAX)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(notificationId++, builder.build())
            }
        }
    }

    companion object {
        var notificationId = 0
    }
}