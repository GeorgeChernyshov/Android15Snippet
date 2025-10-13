package com.example.post35.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.post35.theme.Android15SnippetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(name: String, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineMedium,
                text = name,
                modifier = modifier
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    Android15SnippetTheme {
        AppBar("Title")
    }
}