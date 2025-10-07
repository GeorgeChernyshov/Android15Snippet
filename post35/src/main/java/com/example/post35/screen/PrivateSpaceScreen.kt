package com.example.post35.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.post35.R
import com.example.post35.components.AppBar
import com.example.post35.service.NotificationHelper
import com.example.post35.theme.Android15SnippetTheme

@Composable
fun PrivateSpaceScreen(notificationHelper: NotificationHelper) {
    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = { AppBar(name = stringResource(R.string.label_private_space)) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { HintsBlock() }

            item {
                NotificationsBlock(
                    onStartRepeatingNotificationsClick = {
                        notificationHelper.tryStartRepeatingNotifications()
                    },
                    onStopRepeatingNotificationsClick = {
                        notificationHelper.stopRepeatingNotifications()
                    }
                )
            }
        }
    }
}

@Composable
fun HintsBlock() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(stringResource(R.string.private_space_hint))
        Text(stringResource(R.string.private_space_install_hint))
        Text(stringResource(R.string.private_space_visibility_hint))
    }
}

@Composable
fun NotificationsBlock(
    onStartRepeatingNotificationsClick: () -> Unit,
    onStopRepeatingNotificationsClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.private_space_notification_visibility_hint))

        Button(onClick = onStartRepeatingNotificationsClick) {
            Text(stringResource(R.string.private_space_start_repeating_notifications))
        }

        Button(onClick = onStopRepeatingNotificationsClick) {
            Text(stringResource(R.string.private_space_stop_repeating_notifications))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HintsBlockPreview() {
    Android15SnippetTheme {
        HintsBlock()
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsBlockPreview() {
    Android15SnippetTheme {
        NotificationsBlock(
            onStartRepeatingNotificationsClick = {},
            onStopRepeatingNotificationsClick = {}
        )
    }
}