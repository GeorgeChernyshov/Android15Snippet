package com.example.post35.screen

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.post35.R
import com.example.post35.components.AppBar

@Composable
fun UserInterfaceScreen(onNextClick: () -> Unit) {
    Scaffold(
        topBar = { AppBar(name = stringResource(R.string.label_user_interface)) }
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
                    R.string.ui_predictive_back_supported
                else R.string.ui_predictive_back_not_supported
            ))

            Text(stringResource(
                id = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM)
                    R.string.ui_edge_to_edge_enforced
                else R.string.ui_edge_to_edge_not_enforced
            ))

            Button(onClick = onNextClick) {
                Text(stringResource(R.string.button_next))
            }
        }
    }
}