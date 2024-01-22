package com.training.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.training.compose.lessons.viewmodels.data.LocalTodosDataSource
import com.training.compose.lessons.viewmodels.data.TodosRepositoryImpl
import com.training.compose.lessons.viewmodels.domain.usecase.GetTodosUseCase
import com.training.compose.lessons.viewmodels.ui.TodoViewModel
import com.training.compose.lessons.viewmodels.ui.TodosScreen
import com.training.compose.ui.theme.ComposeTrainingTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrainingTheme {
                // A surface container using the 'background' color from the theme
                val viewModel = viewModel(initializer = { TodoViewModel(
                    getTodosUseCase = GetTodosUseCase(
                        todosRepository = TodosRepositoryImpl(
                            localTodosDataSource = LocalTodosDataSource()
                        )
                    )
                ) })

                val uiState by viewModel.uiState.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    TodosScreen(
                        uiState = uiState,
                        onListCheckedChange = viewModel::onListCheckedChange,
                        addNewTodo = viewModel::addNewTodo
                    )

                }
            }
        }
    }
}
