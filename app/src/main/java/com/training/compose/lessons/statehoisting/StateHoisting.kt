package com.training.compose.lessons.statehoisting

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.training.compose.lessons.statefulandstateless.Person


@Preview(showBackground = true)
@Composable
fun StateHoistingDemo() {

    val textField = remember {
        mutableStateOf("Hello")
    }

    TextField(modifier = Modifier.padding(16.dp),
        value = textField.value,
        onValueChange = { newValue ->
            textField.value = newValue
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCardHoisting(person: Person, checkState: Boolean, onCheckChange: (Boolean) -> Unit) {
    Card(modifier = Modifier
        .width(width = 240.dp),
        onClick = { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = person.name)
            Text(text = person.age.toString())
            IconButton(onClick = { onCheckChange(!checkState) }) {
                Image(
                    painter = painterResource(id = if (checkState) R.drawable.checkbox_on_background else R.drawable.checkbox_off_background),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonCardStateHoistingPreview() {
    var checkState by remember {
        mutableStateOf(false)
    }
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PersonCardHoisting(
                person = Person("Chayanne", 21),
                checkState = checkState,
                onCheckChange = { checkState = it }
            )
        }
    }
}



