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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.post35.R
import com.example.post35.components.AppBar
import com.example.post35.ndk.PageSizeManager
import com.example.post35.theme.Android15SnippetTheme

@Composable
fun CoreFunctionalityScreen(onNextClick: () -> Unit) {
    val pageSize = remember { PageSizeManager().getSystemPageSize() }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = { AppBar(name = stringResource(R.string.label_core_functionality)) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(
                id = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM)
                    R.string.bc_min_sdk_24_cannot
                else R.string.bc_min_sdk_24_can
            ))

            PageSizeBlock(pageSize)

            Button(onClick = onNextClick) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}

@Composable
fun PageSizeBlock(pageSize: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(stringResource(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM)
                R.string.bc_page_size_big
            else R.string.bc_page_size_small
        ))

        Text(stringResource(R.string.bc_page_size_actual, pageSize))
    }
}

@Preview(showBackground = true)
@Composable
fun PageSizeBlockPreview() {
    Android15SnippetTheme {
        PageSizeBlock(pageSize = 16)
    }
}