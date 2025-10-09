package com.example.post35.screen

import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.post35.R
import com.example.post35.components.AppBar
import com.example.post35.service.NotificationHelper
import com.example.post35.theme.Android15SnippetTheme
import com.example.post35.util.FileUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Date
import java.util.Locale

@Composable
fun PrivateSpaceScreen(notificationHelper: NotificationHelper) {
    var pickedFileContent by remember { mutableStateOf("No file picked.") }

    val context = LocalContext.current

    val pickTextFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument() // Use OpenDocument to pick any file
    ) { uri: Uri? ->
        uri?.let {
            try {
                context.contentResolver.openInputStream(it)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).use { reader ->
                        pickedFileContent = reader.readText()
                    }
                }
            } catch (e: Exception) {
                pickedFileContent = "Error reading file: ${e.message}"
            }
        }
    }

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

            item {
                ContentVisibilityBlock(
                    pickedFileContent = pickedFileContent,
                    onCreateFileClick = {
                        val currentTime = SimpleDateFormat(
                            "HHmmss",
                            Locale.getDefault()
                        ).format(Date())

                        val fileName = "test_file$currentTime.txt"
                        val content = "This file was created by app at $currentTime."
                        FileUtil.saveTextFile(
                            context = context,
                            fileName = fileName,
                            content = content
                        )
                    },
                    onReadFileClick = {
                        val testFileName = "test_file%"
                        val content = FileUtil.readTextFile(context, testFileName)
                        pickedFileContent = content ?: "File '$testFileName' not found."
                    },
                    onPickFileClick = {
                        pickTextFileLauncher.launch(arrayOf("text/plain"))
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

@Composable
fun ContentVisibilityBlock(
    pickedFileContent: String,
    onCreateFileClick: () -> Unit,
    onReadFileClick: () -> Unit,
    onPickFileClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.private_space_storage_hint))

        Button(onClick = onCreateFileClick) {
            Text(stringResource(R.string.private_space_create_file_button))
        }

        Button(onClick = onReadFileClick) {
            Text(stringResource(R.string.private_space_read_file_button))
        }

        Text(stringResource(R.string.private_space_pick_file_hint))

        Button(onClick = onPickFileClick) {
            Text(stringResource(R.string.private_space_pick_file_button))
        }

        // Display content of the picked file
        Text(stringResource(
            R.string.private_space_picked_file_content,
            pickedFileContent
        ))
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

@Preview(showBackground = true)
@Composable
fun ContentVisibilityBlockPreview() {
    Android15SnippetTheme {
        ContentVisibilityBlock(
            pickedFileContent = "Picked file content",
            onCreateFileClick = {},
            onReadFileClick = {},
            onPickFileClick = {}
        )
    }
}