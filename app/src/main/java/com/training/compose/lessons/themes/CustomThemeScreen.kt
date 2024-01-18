package com.training.compose.lessons.themes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomThemeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Normal button with default theme
        Button(
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Button")
        }

        //Custom button with custom theme overriden
        MyCustomTheme(
            darkTheme = true
        ) {


            Button(onClick = { /*TODO*/ }) {
                Text(text = "Button")
            }


            //Mix of custom theme and Material or other theme
            MaterialTheme(
                typography = Typography(
                    headlineLarge = TextStyle(color = com.training.compose.ui.theme.Pink80)
                )
            ) {
                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(size = 12.dp)
                ) {
                    Text(
                        text = "Another Theme even",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }

            //override styles to atomic components
            Row {
                Text(
                    text = "Hola ",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = com.training.compose.ui.theme.Purple80,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "a todos",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = com.training.compose.ui.theme.Pink40

                    )
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun CustomThemeScreenPreview() {
    MyCustomTheme {
        CustomThemeScreen()
    }
}