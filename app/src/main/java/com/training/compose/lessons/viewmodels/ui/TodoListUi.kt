package com.training.compose.lessons.viewmodels.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.compose.lessons.viewmodels.domain.entity.Todo


@Composable
fun TodoList(list: List<Todo>, onCheckedChange: (Int, Boolean) -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(list) { index,todo ->
            TodoItem(todo) {
                onCheckedChange(index, it)
            }
        }
    }
}

@Composable
private fun TodoItem(todo: Todo, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .size(72.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(todo.title, style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            ))
            Checkbox(checked = todo.isDone, onCheckedChange = onCheckedChange)

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemsPreview(){
    val list = run {
        buildList {
            for (i in 0..5){
                add(Todo(
                    id = i,
                    title = "Todo $i",
                    isDone = false
                ))
            }
        }
    }
    MaterialTheme{
        TodoList(list = list, onCheckedChange = {_, _ ->})
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoItemPreview() {
    MaterialTheme {
        TodoItem(todo = Todo(
            id = 0,
            title = "Learn Compose",
            isDone = false
        ), onCheckedChange = {})
    }
}