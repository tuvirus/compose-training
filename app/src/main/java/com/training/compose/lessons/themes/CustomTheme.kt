package com.training.compose.lessons.themes

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.training.compose.R

private fun DarkColorsTheme(context: Context) = darkColorScheme(
    primary = Color(context.getColor(R.color.primary_color)),
    secondary = Color(context.getColor(R.color.secondary_color)),
    tertiary = Color(context.getColor(R.color.tertiary_color)),
    onPrimary = Color(context.getColor(R.color.white))
)

private fun LightColorsTheme(context: Context) = lightColorScheme(
    primary = Color(context.getColor(R.color.purple_700)),
    secondary = Color(context.getColor(R.color.purple_500)),
    tertiary = Color(context.getColor(R.color.teal_200))
)

@Composable
fun MyCustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val context = LocalContext.current

    val colorScheme = if (darkTheme){
        DarkColorsTheme(context)
    }else{
        LightColorsTheme(context)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}