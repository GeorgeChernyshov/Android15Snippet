package com.example.post35.screen

import android.os.Build
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.post35.components.AppBar
import com.example.post35.R
import com.example.post35.service.NotificationHelper

@Composable
fun BehaviorChangesScreen(notificationHelper: NotificationHelper) {
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
            Text(
                text = stringResource(R.string.bc_screen_recording_hint),
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM)
                        R.string.bc_screen_recording_hidden
                    else R.string.bc_screen_recording_visible
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { notificationHelper.handleSimpleNotification() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.bc_send_notification))
            }
        }
    }
}