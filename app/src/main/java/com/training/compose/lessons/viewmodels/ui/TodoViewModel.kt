package com.training.compose.lessons.viewmodels.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.compose.lessons.viewmodels.domain.entity.Todo
import com.training.compose.lessons.viewmodels.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _uiState.value = TodoUiState.Loading
            getTodosUseCase().collect {
                _uiState.value = TodoUiState.Success(it)
            }
        }
    }

    fun addNewTodo() {
        val currentState = _uiState.value
        if(currentState is TodoUiState.Success){
            val list = currentState.todos.toMutableList()
            list.add(
                Todo(
                    id = list.size,
                    title = "New Todo ${list.size - 2}",
                    isDone = false
                )
            )
            _uiState.value = TodoUiState.Success(list)
        }
    }

    fun onListCheckedChange(todoIndex: Int, isChecked: Boolean) {
        val currentState = _uiState.value
        if(currentState is TodoUiState.Success){

            val list = currentState.todos.toMutableList()

            list[todoIndex].copy(isDone = isChecked).let {newTodo ->
                list[todoIndex] = newTodo
            }

            _uiState.value = TodoUiState.Success(list)
        }
    }
}

sealed interface TodoUiState {
    object Loading : TodoUiState
    data class Success(
        val todos: List<Todo>
    ) : TodoUiState

}