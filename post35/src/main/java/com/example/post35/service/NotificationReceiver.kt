package com.example.post35.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.example.post35.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        intent ?: return

        if (intent.action == ACTION_SHOW_NOTIFICATION) {
            val title = intent.getStringExtra("NOTIFICATION_TITLE")
                ?: context.getString(R.string.notification_title)

            val text = intent.getStringExtra("NOTIFICATION_TEXT")
                ?: context.getString(R.string.notification_text)

            val sender = NotificationSender(context)

            sender.showNotification(title, text)
            sender.scheduleNextNotification(
                SystemClock.elapsedRealtime() + NotificationSender.NOTIFICATION_INTERVAL_MILLIS
            )
        }
    }

    companion object {
        const val ACTION_SHOW_NOTIFICATION = "com.example.post35.SHOW_NOTIFICATION"
    }
}