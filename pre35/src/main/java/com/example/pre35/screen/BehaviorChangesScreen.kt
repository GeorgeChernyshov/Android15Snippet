package com.example.pre35.screen

import android.app.NotificationManager
import android.app.NotificationManager.INTERRUPTION_FILTER_ALL
import android.app.NotificationManager.INTERRUPTION_FILTER_NONE
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pre35.components.AppBar
import com.example.pre35.R

@Composable
fun BehaviorChangesScreen() {
    val context = LocalContext.current
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

    val activityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        toggleDND(notificationManager)
    }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = { AppBar(name = stringResource(R.string.label_behavior_changes)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.bc_toggle_dnd_hint))

            Button(
                onClick = {
                    if (!notificationManager.isNotificationPolicyAccessGranted) {
                        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                        activityLauncher.launch(intent)
                    } else toggleDND(notificationManager)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.bc_toggle_dnd))
            }
        }
    }
}

fun toggleDND(notificationManager: NotificationManager) {
    notificationManager.setInterruptionFilter(
        if (notificationManager.currentInterruptionFilter == INTERRUPTION_FILTER_NONE)
            INTERRUPTION_FILTER_ALL
        else INTERRUPTION_FILTER_NONE
    )
}