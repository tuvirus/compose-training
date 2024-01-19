package com.training.compose.lessons.statefulandstateless

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//STATELESS COMPOSABLE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard(person: Person, checkState: MutableState<Boolean>) {
    Card(modifier = Modifier
        .width(width = 240.dp),
        onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = person.name)
            Text(text = person.age.toString())
            IconButton(onClick = { checkState.value = !checkState.value }) {
                Image(
                    painter = painterResource(id = if (checkState.value) android.R.drawable.checkbox_on_background else android.R.drawable.checkbox_off_background),
                    contentDescription = ""
                )
            }
        }
    }
}

//STATEFUL COMPOSABLE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCardStateful(person: Person) {
    val checkState = remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .width(width = 240.dp),
        onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = person.name)
            Text(text = person.age.toString())
            IconButton(onClick = { checkState.value = !checkState.value }) {
                Image(
                    painter = painterResource(id = if (checkState.value) android.R.drawable.checkbox_on_background else android.R.drawable.checkbox_off_background),
                    contentDescription = ""
                )
            }
        }
    }
}

data class Person(
    val name: String,
    val age: Int
)

@Preview(showBackground = true)
@Composable
private fun PersonCardPreview() {
    val checkState = remember {
        mutableStateOf(false)
    }
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PersonCardStateful(
                Person("Carlos", 18),
                //checkState
            )
        }
    }

}