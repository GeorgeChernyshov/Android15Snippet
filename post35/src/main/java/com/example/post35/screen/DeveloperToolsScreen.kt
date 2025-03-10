package com.example.post35.screen

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.post35.R
import com.example.post35.components.AppBar
import com.example.post35.theme.roboto

@Composable
fun DeveloperToolsScreen() {
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
            AndroidView(factory = { context ->
                TextView(context).apply {
                    val spannable = SpannableString(
                        ContextCompat.getString(
                            context,
                            R.string.dev_tools_flex_string
                        )
                    )

                    spannable.setSpan(StyleSpan(Typeface.BOLD), 0, spannable.length - 7, 0)

                    text = spannable

                    roboto(context)?.let {
                        typeface = Typeface
                            .CustomFallbackBuilder(it)
                            .build()
                    }
                }
            })
        }
    }
}