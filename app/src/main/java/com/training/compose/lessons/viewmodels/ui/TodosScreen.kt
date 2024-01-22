@file:OptIn(ExperimentalMaterial3Api::class)

package com.training.compose.lessons.viewmodels.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodosScreen(
    uiState: TodoUiState,
    onListCheckedChange: (Int, Boolean) -> Unit,
    addNewTodo: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "ViewModels") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colors.primary,
                    titleContentColor = MaterialTheme.colors.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = addNewTodo) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new todo")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {

        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (uiState) {
                is TodoUiState.Loading -> LoadingList(
                    modifier = Modifier.align(Alignment.Center)
                )

                is TodoUiState.Success -> {
                    TodoList(
                        list = uiState.todos,
                        onCheckedChange = onListCheckedChange
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingList(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun TodosScreenPreview() {
    MaterialTheme {
        TodosScreen(uiState = TodoUiState.Loading, onListCheckedChange = { _, _ -> }) {}
    }
}