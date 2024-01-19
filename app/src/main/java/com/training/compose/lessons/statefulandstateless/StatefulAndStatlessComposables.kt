package com.training.compose.lessons.statefulandstateless

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.training.compose.lessons.themes.MyCustomTheme
import kotlin.random.Random

//we say it's a stateful composable because it hold a state inside the composable ( using remember )
@Preview(showBackground = true)
@Composable
fun StatefulComposable() {
    val num = remember {
        mutableIntStateOf(
            Random.nextInt(0,10)
        )
    }
    Text(modifier = Modifier.padding(16.dp),text = num.intValue.toString())
}

//A stateless composable because it doesn't hold any state inside the composable
//Instead we pass state to it as a parameter
@Composable
fun StatelessComposable(textToShow: State<String>) {
    Text(modifier = Modifier.padding(16.dp),text = textToShow.value)
}

//Pure form of stateless composable
//As a plus, being stateless make it easier to test
@Composable
fun StatelessComposable2(textToShow: String) {
    Text(modifier = Modifier.padding(16.dp),text = textToShow)
}

// Usually we have mixed stateful (screens) and stateless components
@Preview(showBackground = true)
@Composable
private fun StatelessComposablePreview() {
    val textToShow = remember {
        mutableStateOf("Hello")
    }
    MyCustomTheme {
        Column {
            StatelessComposable(textToShow = textToShow)

            StatelessComposable2(textToShow = textToShow.value)
        }
    }
}
