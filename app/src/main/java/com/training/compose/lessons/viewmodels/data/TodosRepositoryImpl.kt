package com.training.compose.lessons.viewmodels.data

import com.training.compose.lessons.viewmodels.domain.entity.Todo
import com.training.compose.lessons.viewmodels.domain.repository.TodosRepository
import kotlinx.coroutines.flow.Flow

class TodosRepositoryImpl(
    private val localTodosDataSource: TodosDataSource
): TodosRepository {
    override fun getTodos(): Flow<List<Todo>> {
        return localTodosDataSource.getTodos()
    }
}