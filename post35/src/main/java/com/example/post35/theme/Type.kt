package com.example.post35.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.post35.R

fun roboto(context: Context) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
    android.graphics.fonts.FontFamily.Builder(
        android.graphics.fonts.Font.Builder(
            context.resources,
            R.font.roboto_variable
        ).build()
    ).buildVariableFamily()
} else {
    android.graphics.fonts.FontFamily
        .Builder(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 400")
                .setWeight(400)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 100")
                .setWeight(100)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 200")
                .setWeight(200)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 300")
                .setWeight(300)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 500")
                .setWeight(500)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 600")
                .setWeight(600)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 700")
                .setWeight(700)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 800")
                .setWeight(800)
                .build()
        )
        .addFont(
            android.graphics.fonts.Font.Builder(context.resources, R.font.roboto_variable)
                .setFontVariationSettings("'wght' 900")
                .setWeight(900)
                .build()
        )
        .build()
}


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)